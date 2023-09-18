package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.domain.model.Customer;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GenerateOrderTest {

    @Mock
    AddProductUseCase addProductUseCase;

    @InjectMocks
    GenerateOrder generateOrderService;

    @Test
    void create() {
        // GIVEN
        var product1 = ProductRequest.newBuilder()
                .setProductId(56)
                .setQuantity(1)
                .build();
        var product2 = ProductRequest.newBuilder()
                .setProductId(23)
                .setQuantity(2)
                .build();

        // WHEN

        StreamRecorder<OrderResponse> orderResponseObserver = StreamRecorder.create();
        var productsObserver = generateOrderService.create(orderResponseObserver);

        for (int i = 0; i < 10; i++) {
            productsObserver.onNext(product1);
            productsObserver.onNext(product2);
        }
        productsObserver.onCompleted();

        // THEN

        var orderResponses = orderResponseObserver.getValues();

        assertEquals(1, orderResponses.size());

        verify(addProductUseCase, times(20)).addProduct(any(Customer.class), any());

    }
}