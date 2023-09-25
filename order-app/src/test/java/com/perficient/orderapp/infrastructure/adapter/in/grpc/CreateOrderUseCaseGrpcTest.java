package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CustomerRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.application.CreateOrderUseCase;
import com.perficient.orderapp.domain.Order;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseGrpcTest {

    @Mock
    CreateOrderUseCase createOrderUseCase;

    @InjectMocks
    CreateOrderServiceGrpc createOrderServiceGrpc;

    @Test
    void create_order_should_success() {

        // GIVEN
        var customerId = UUID.randomUUID();
        var customerRequest = CustomerRequest.newBuilder()
                .setId(customerId.toString())
                .build();
        var order = Order.builder()
                .orderId(UUID.randomUUID())
                .customerId(customerId)
                .build();

        StreamRecorder<OrderResponse> responseObserver = StreamRecorder.create();
        given(createOrderUseCase.create(customerId)).willReturn(order);

        // WHEN
        createOrderServiceGrpc.create(customerRequest, responseObserver);

        // THEN

        var orderResponse = responseObserver.getValues();

        assertEquals(1, orderResponse.size());
        assertNotNull(orderResponse.get(0).getOrderId());
        assertNotNull(orderResponse.get(0).getCustomerId());
    }
}