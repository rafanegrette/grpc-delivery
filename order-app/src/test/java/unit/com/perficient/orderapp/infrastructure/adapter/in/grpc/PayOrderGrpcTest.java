package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.application.PayOrderUseCase;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.mother.OrderMother;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentRequest;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PayOrderGrpcTest {

    @Mock
    PayOrderUseCase payOrderUseCase;
    @InjectMocks
    PayCartGrpc payCartGrpc;

    @Test
    void payProducts() {

        // Given
        // cart = CartMother.cart.build;
        PaymentRequest paymentRequest = PaymentRequest.newBuilder()
                .setCustomerId(CustomerMother.customerId.toString())
                .setPaymentMethod("Cash")
                .build();

        StreamRecorder<OrderResponse> orderResponseObserver = StreamRecorder.create();
        given(payOrderUseCase.pay(CustomerMother.customerId)).willReturn(OrderMother.order.build());

        // When
        payCartGrpc.payOrder(paymentRequest, orderResponseObserver);

        var orderList = orderResponseObserver.getValues();

        // Then
        assertNotNull(orderList);
        assertFalse(orderList.isEmpty());
        assertNotEquals(0, orderList.get(0).getCreationDate().getSeconds());
        assertEquals(OrderMother.AMOUNT.doubleValue(), orderList.get(0).getPaymentDetail().getAmount());
    }
}
