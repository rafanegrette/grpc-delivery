package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.CreateOrderUseCase;
import com.perficient.orderapp.application.port.out.SaveOrder;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class CreateOrderService implements CreateOrderUseCase {


    SaveOrder saveOrder;

    @Override
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
