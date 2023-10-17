package com.perficient.orderapp.ingrastructure.adapter.in.grpc;

import com.perficient.orderapp.domain.excepton.ProductNotFoundException;
import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.AddProductsToCartServiceGrpc;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.exception.ErrorHandler;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.AddProductRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartServiceGrpc;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.config.CreateKeySpace;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CartEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CustomerEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.MenuResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.inprocess.InProcessChannelBuilder;
import org.junit.jupiter.api.*;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {"grpc.inProcessServerName=testServerForCart",
        "grpc.enabled=false"})
@ContextConfiguration(initializers = AddProductsITTest.TestAppContextInitializer.class)
@EnableAutoConfiguration(exclude = CassandraDataAutoConfiguration.class)
public class AddProductsITTest {
    static class TestAppContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {
        @Override
        public void initialize(GenericApplicationContext applicationContext) {
            applicationContext.setAllowCircularReferences(false);
        }
    }

    @MockBean
    CassandraCustomerRepository cassandraCustomerRepository;
    @MockBean
    CassandraCartRepository cassandraCartRepository;
    @MockBean
    CassandraOrderRepository cassandraOrderRepository;
    @MockBean
    FetchProductsGrpc.FetchProductsBlockingStub productsGrpcApi;
    @MockBean
    CreateKeySpace createKeySpace;
    protected ManagedChannel inProcChannel;
    protected Channel seletedChannel;
    @MockBean
    ErrorHandler errorHandler;

    @Autowired
    protected GRpcServerProperties gRpcServerProperties;

    @BeforeAll
    public void setupChannels() throws IOException {
        if (StringUtils.hasText(gRpcServerProperties.getInProcessServerName())) {
            inProcChannel = InProcessChannelBuilder
                    .forName(gRpcServerProperties.getInProcessServerName())
                    .usePlaintext()
                    .build();
        } else {
            fail( "No in-processChannel Available");
        }
        seletedChannel = inProcChannel;
    }

    @AfterAll
    public void shutdownChannels() {
        inProcChannel.shutdownNow();
    }

    @Test
    @DisplayName("Given a stream of valid product added- when finish - should return the summary cart")
    void addProductTest() throws InterruptedException {

        //Given

        CartServiceGrpc.CartServiceStub cartService = CartServiceGrpc.newStub(seletedChannel);
        var productRequest1 = AddProductRequest.newBuilder()
                .setId(ProductItemMother.product_id_1.toString())
                .setCustomerId(CustomerMother.customerId.toString())
                .setQuantity(1)
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        CartStreamingObserver cartResponseObserver = new CartStreamingObserver(latch);

        given(productsGrpcApi.getProduct(any())).willReturn(MenuResponse.newBuilder()
                .setProductId(ProductItemMother.product_id_1.toString())
                .setCategory("")
                .setMenuId(1)
                .setPrice(45)
                .build());

        var customerEntity = CustomerEntityMapper.INSTANCE.map(CustomerMother.customer.build());
        given(cassandraCustomerRepository.findById(CustomerMother.customerId)).willReturn(Optional.of(customerEntity));
        var cartEntity = CartEntityMapper.INSTANCE.map(CartMother.cart.build());
        given(cassandraCartRepository.findById(customerEntity.getCartId())).willReturn(Optional.of(cartEntity));
        // When
        var productsObserver = cartService.addProduct(cartResponseObserver);
        productsObserver.onNext(productRequest1);
        productsObserver.onCompleted();
        latch.await();

        // Then
        var cartResponse = cartResponseObserver.getCart();
        assertNotNull(cartResponse);
        assertEquals(2, cartResponse.getProductsList().size());
    }


    @Test
    @DisplayName("Given a stream with a non existing product - should Handle Product Not Found Exception")
    void addProductTestThrowProductNotFound() throws InterruptedException {

        //Given
        CartServiceGrpc.CartServiceStub cartService = CartServiceGrpc.newStub(seletedChannel);
        var productRequest1 = AddProductRequest.newBuilder()
                .setId(ProductItemMother.product_id_1.toString())
                .setCustomerId(CustomerMother.customerId.toString())
                .setQuantity(1)
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        CartStreamingObserver cartResponseObserver = new CartStreamingObserver(latch);

        doThrow(StatusRuntimeException.class)
                .when(productsGrpcApi)
                .getProduct(any());

        var cartEntity = CartEntityMapper.INSTANCE.map(CartMother.cart.build());
        var customerEntity = CustomerEntityMapper.INSTANCE.map(CustomerMother.customer.build());
        given(cassandraCustomerRepository.findById(CustomerMother.customerId)).willReturn(Optional.of(customerEntity));
        given(cassandraCartRepository.findById(customerEntity.getCartId())).willReturn(Optional.of(cartEntity));

        // When
            var productsObserver = cartService.addProduct(cartResponseObserver);
            productsObserver.onNext(productRequest1);
            productsObserver.onCompleted();
            latch.await();



        // Then
        verify(errorHandler, times(1)).handle(any(ProductNotFoundException.class), any());


    }

}
