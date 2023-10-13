package com.perficient.orderapp.ingrastructure.adapter.in.grpc;

import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentServiceGrpc;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.config.CreateKeySpace;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CartEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CustomerEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import org.junit.jupiter.api.*;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = {"grpc.inProcessServerName=testServerForPayment",
        "grpc.enabled=false"})
@ContextConfiguration(initializers = AddProductsITTest.TestAppContextInitializer.class )
@EnableAutoConfiguration(exclude = CassandraDataAutoConfiguration.class)
public class PayOrderITTest {

    @MockBean
    CassandraCustomerRepository cassandraCustomerRepository;
    @MockBean
    CassandraCartRepository cassandraCartRepository;
    @MockBean
    CassandraOrderRepository cassandraOrderRepository;
    @MockBean
    CreateKeySpace createKeySpace;
    protected ManagedChannel inProcChannel;
    protected Channel seletedChannel;

    @Autowired
    protected GRpcServerProperties gRpcServerProperties;

    @BeforeEach
    public void setupChannels() throws IOException {
        if (StringUtils.hasText(gRpcServerProperties.getInProcessServerName())) {
            inProcChannel = InProcessChannelBuilder
                    .forName(gRpcServerProperties.getInProcessServerName())
                    .usePlaintext()
                    .build();
        } else {
            assertTrue(false, "No in-processChannel Available");
        }
        seletedChannel = inProcChannel;
    }


    @AfterAll
    public void shutdownChannels() {
        inProcChannel.shutdownNow();
    }

    @Test
    @DisplayName("Given a Cart with products when pay should return the order")
    void payOrder() {
        // Given
        var paymentService = PaymentServiceGrpc.newBlockingStub(seletedChannel);
        var paymentRequest = PaymentRequest.newBuilder()
                .setPaymentMethod("CASH")
                .setCustomerId(CustomerMother.customerId.toString())
                .build();
        var customerEntity = CustomerEntityMapper.INSTANCE.map(CustomerMother.customer.build());
        var cartEntity = CartEntityMapper.INSTANCE.map(CartMother.cart.build());

        given(cassandraCustomerRepository.findById(CustomerMother.customerId)).willReturn(Optional.of(customerEntity));
        given(cassandraCartRepository.findById(customerEntity.getCartId())).willReturn(Optional.of(cartEntity));

        // When

        var orderResponse = paymentService.payOrder(paymentRequest);

        // Then
        assertNotNull(orderResponse);
        assertNotEquals(0, orderResponse.getCreationDate().getSeconds());
        assertEquals(2, orderResponse.getProductsList().size());
    }
}
