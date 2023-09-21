package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.RetrieveOrderUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOrderService implements RetrieveOrderUseCase {
    @Override
    public Order retrieve(Customer customer) {
        return null;
    }
}
