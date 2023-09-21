package com.perficient.orderapp.application.port.in;

import com.perficient.orderapp.domain.model.Order;

import java.util.UUID;

public interface CreateOrderUseCase {

    Order create(UUID customerId);
}
