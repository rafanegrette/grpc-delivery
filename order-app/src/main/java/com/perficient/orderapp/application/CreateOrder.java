package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;

import java.util.HashSet;
import java.util.UUID;

public class CreateOrder {

    public Order createOrder(UUID customerId) {
        UUID id = UUID.randomUUID();

        return Order.builder()
                .orderId(id)
                .customerId(customerId)
                .productItems(new HashSet<ProductItem>())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();

    }

}
