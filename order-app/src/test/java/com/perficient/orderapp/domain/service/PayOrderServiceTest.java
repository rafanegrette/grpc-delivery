package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.out.PaymentPort;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.OrderStatus;
import com.perficient.orderapp.domain.model.PaymentDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PayOrderServiceTest {

    @Mock
    PaymentPort paymentPort;

    @InjectMocks
    PayOrderService payOrderService;

    @Test
    void payOrder_should_success() {
        // GIVEN
        var order = Order.builder()
                .orderId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();
        var paymentDetails = new PaymentDetails(UUID.randomUUID(),
                LocalDateTime.now(),
                BigDecimal.valueOf(50.0));
        given(paymentPort.executePayment(order)).willReturn(paymentDetails);

        // WHEN
        payOrderService.payOrder(order);

        // THEN
        assertEquals(OrderStatus.PAID, order.getOrderStatus());
        assertNotNull(order.getPaymentDetails());
    }
}