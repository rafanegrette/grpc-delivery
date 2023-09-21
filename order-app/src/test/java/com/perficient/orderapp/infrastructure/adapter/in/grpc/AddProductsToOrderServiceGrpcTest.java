package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.in.RetrieveOrderUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.OrderStatus;
import com.perficient.orderapp.domain.model.ProductItem;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddProductsToOrderServiceGrpcTest {

    final String ORDER_ID = "15262843-0aa7-428f-a36d-f59d5ead018f";
    final String PRODUCT_ID_1 = "ca920c2f-3e90-491e-b98c-96b7d32b0e9c";
    final String PRODUCT_ID_2 = "7c303f86-19e7-4257-b216-9e24d56972eb";
    final UUID PRODUCT_ID_1_UUID = UUID.fromString(PRODUCT_ID_1);
    final UUID PRODUCT_ID_2_UUID = UUID.fromString(PRODUCT_ID_2);
    final String PRODUCT_NAME_1 = "Subway with jam";
    final String PRODUCT_NAME_2 = "Hamburger Avenger";

    final UUID CUSTOMER_ID = UUID.fromString("6d303f86-3e90-491e-b98c-96b7d32b0e9d");

    @Mock
    AddProductUseCase addProductUseCase;
    @Mock
    RetrieveOrderUseCase retrieveOrderUseCase;

    @InjectMocks
    AddProductsToOrderServiceGrpc createOrderServiceServiceGrpc;

    @Test
    void AddProductsToOrder() {
        // GIVEN
        var productRequest1 = ProductRequest.newBuilder()
                .setId(PRODUCT_ID_1)
                .setQuantity(1)
                .setOrderId(ORDER_ID)
                .build();
        var productRequest2 = ProductRequest.newBuilder()
                .setId(PRODUCT_ID_2)
                .setQuantity(2)
                .setOrderId(ORDER_ID)
                .build();


        // WHEN

        StreamRecorder<OrderResponse> orderResponseObserver = StreamRecorder.create();
        var productsObserver = createOrderServiceServiceGrpc.addProductToOrder(orderResponseObserver);

        for (int i = 0; i < 10; i++) {
            productsObserver.onNext(productRequest1);
            productsObserver.onNext(productRequest2);
        }
        productsObserver.onCompleted();

        // THEN

        var orderResponses = orderResponseObserver.getValues();

        assertEquals(1, orderResponses.size());

        verify(addProductUseCase, times(10)).addProductToOrder(any(Order.class), eq(PRODUCT_ID_1_UUID), eq(1));
        verify(addProductUseCase, times(10)).addProductToOrder(any(Order.class), eq(PRODUCT_ID_2_UUID), eq(2));

    }

    @Test
    void addProducttToOrderReviewProducts() {
        // GIVEN

        var productRequest1 = ProductRequest.newBuilder()
                .setId(PRODUCT_ID_1)
                .setQuantity(1)
                .setOrderId(ORDER_ID)
                .build();
        var productRequest2 = ProductRequest.newBuilder()
                .setId(PRODUCT_ID_2)
                .setQuantity(2)
                .setOrderId(ORDER_ID)
                .build();

        var product1 = new ProductItem(UUID.fromString(PRODUCT_ID_1),
                PRODUCT_NAME_1,
                "Vegetables",
                1,
                BigDecimal.TEN,
                BigDecimal.ZERO);
        var product2 = new ProductItem(UUID.fromString(PRODUCT_ID_2),
                PRODUCT_NAME_2,
                "Vegetables",
                2,
                BigDecimal.valueOf(5.5),
                BigDecimal.ZERO);

        var orderGiven = getOrder(Set.of(product1, product2));

        // WHEN
        when(retrieveOrderUseCase.retrieveCurrentOrder(any(Customer.class))).thenReturn(orderGiven);

        StreamRecorder<OrderResponse> orderResponseObserver = StreamRecorder.create();
        var productsObserver = createOrderServiceServiceGrpc.addProductToOrder(orderResponseObserver);

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

    private Order getOrder(Set<ProductItem> productItems) {
        return new Order(UUID.randomUUID(),
                CUSTOMER_ID,
                productItems,
                BigDecimal.TEN,
                null,
                OrderStatus.IN_PROGRESS);
    }

}