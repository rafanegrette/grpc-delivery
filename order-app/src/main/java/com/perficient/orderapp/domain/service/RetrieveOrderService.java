package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.RetrieveOrderUseCase;
import com.perficient.orderapp.application.port.out.RetrieveOrder;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOrderService implements RetrieveOrderUseCase {

    RetrieveOrder retrieveOrder;

    @Override
    public Order retrieveCurrentOrder(Customer customer) {
        var order = retrieveOrder.retrieveLastOrder(customer);
        return order;
    }
}
