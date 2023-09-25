package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.RetrieveOrder;
import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOrderUseCase {

    RetrieveOrder retrieveOrder;

    public Order retrieveCurrentOrder(Customer customer) {
        return retrieveOrder.retrieveLastOrder(customer);
    }
}
