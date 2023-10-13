package com.perficient.orderapp.domain.mother;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.PaymentDetails;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class OrderMother {
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(556.3);
    public static Order.OrderBuilder order = Order.builder()
            .orderId(UUID.randomUUID())
            .orderStatus(OrderStatus.COMPLETED)
            .customerId(CustomerMother.customerId)
            .creationDate(LocalDateTime.now().minusHours(5))
            .paymentDetails(PaymentDetails.builder()
                    .id(UUID.randomUUID())
                    .paymentDate(LocalDateTime.now())
                    .amount(AMOUNT)
                    .build())
            .totalPrice(AMOUNT)
            .productItems(Map.of(ProductItemMother.product1.build(), 1,
                    ProductItemMother.product2.build(), 1));
}
