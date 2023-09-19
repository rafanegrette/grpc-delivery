package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.CreateOrderUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService implements CreateOrderUseCase {

    @Override
    public Order create(Customer customer) {
        return null;
    }
}
