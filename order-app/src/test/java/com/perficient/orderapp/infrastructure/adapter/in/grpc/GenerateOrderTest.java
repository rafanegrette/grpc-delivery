package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Product;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

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
    void createAddProducts() {
        // GIVEN
        var productId1 = "ca920c2f-3e90-491e-b98c-96b7d32b0e9c";
        var productId2 = "7c303f86-19e7-4257-b216-9e24d56972eb";
        var productRequest1 = ProductRequest.newBuilder()
                .setProductId(productId1)
                .setQuantity(1)
                .build();
        var productRequest2 = ProductRequest.newBuilder()
                .setProductId(productId2)
                .setQuantity(2)
                .build();

        var product1 = new Product(UUID.fromString(productId1), 1);
        var product2 = new Product(UUID.fromString(productId2), 2);

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

}