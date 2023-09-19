package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.in.CreateOrderUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.Product;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenerateOrderTest {

    final String PRODUCT_ID_1 = "ca920c2f-3e90-491e-b98c-96b7d32b0e9c";
    final String PRODUCT_ID_2 = "7c303f86-19e7-4257-b216-9e24d56972eb";
    final String PRODUCT_NAME_1 = "Subway with jam";
    final String PRODUCT_NAME_2 = "Hamburger Avenger";

    final Customer CUSTOMER = new Customer("guy1");
    @Mock
    AddProductUseCase addProductUseCase;
    @Mock
    CreateOrderUseCase createOrderUseCase;

    @InjectMocks
    GenerateOrder generateOrderService;

    @Test
    void createAddProducts() {
        // GIVEN
        var productRequest1 = ProductRequest.newBuilder()
                .setProductId(PRODUCT_ID_1)
                .setQuantity(1)
                .build();
        var productRequest2 = ProductRequest.newBuilder()
                .setProductId(PRODUCT_ID_2)
                .setQuantity(2)
                .build();

        var product1 = new Product(UUID.fromString(PRODUCT_ID_1), PRODUCT_NAME_1, 1);
        var product2 = new Product(UUID.fromString(PRODUCT_ID_2), PRODUCT_NAME_2,2);

        // WHEN

        StreamRecorder<OrderResponse> orderResponseObserver = StreamRecorder.create();
        var productsObserver = generateOrderService.create(orderResponseObserver);

        for (int i = 0; i < 10; i++) {
            productsObserver.onNext(productRequest1);
            productsObserver.onNext(productRequest2);
        }
        productsObserver.onCompleted();

        // THEN

        var orderResponses = orderResponseObserver.getValues();

        assertEquals(1, orderResponses.size());

        verify(addProductUseCase, times(10)).addProduct(any(Customer.class), eq(product1));
        verify(addProductUseCase, times(10)).addProduct(any(Customer.class), eq(product2));

    }

    @Test
    void createOrderReviewProducts() {
        // GIVEN

        var productRequest1 = ProductRequest.newBuilder()
                .setProductId(PRODUCT_ID_1)
                .setQuantity(1)
                .build();
        var productRequest2 = ProductRequest.newBuilder()
                .setProductId(PRODUCT_ID_2)
                .setQuantity(2)
                .build();

        var product1 = new Product(UUID.fromString(PRODUCT_ID_1), PRODUCT_NAME_1,1);
        var product2 = new Product(UUID.fromString(PRODUCT_ID_2), PRODUCT_NAME_2,2);
        var orderGiven = getOrder(List.of(product1, product2));

        // WHEN
        when(createOrderUseCase.create(CUSTOMER)).thenReturn(orderGiven);

        StreamRecorder<OrderResponse> orderResponseObserver = StreamRecorder.create();
        var productsObserver = generateOrderService.create(orderResponseObserver);

        for (int i = 0; i < 10; i++) {
            productsObserver.onNext(productRequest1);
            productsObserver.onNext(productRequest2);
        }
        productsObserver.onCompleted();

        // THEN

        var orderResponses = orderResponseObserver.getValues();

        assertEquals(1, orderResponses.size());
        assertEquals(2, orderResponses.get(0).getProductsList().size());
    }

    private Order getOrder(List<Product> products) {
        return new Order("jdsfkdsj7880", CUSTOMER, products);
    }

}