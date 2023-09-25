package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.SaveOrder;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class CreateOrderUseCase {


    SaveOrder saveOrder;

    public Order create(UUID customerId) {
        var orderId = UUID.randomUUID();

        var order = Order.builder()
                .orderId(orderId)
                .customerId(customerId)
                .productItems(new HashSet<>())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();
        saveOrder.save(order);
        return order;
    }
}
