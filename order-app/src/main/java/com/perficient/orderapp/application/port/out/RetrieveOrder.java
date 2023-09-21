package com.perficient.orderapp.application.port.out;

import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;

import java.util.UUID;

public interface RetrieveOrder {
    Order retrieve(UUID orderId);

    Order retrieveLastOrder(Customer customer);
}
