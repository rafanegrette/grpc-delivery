package com.perficient.orderapp.domain.port;

import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.domain.Order;

import java.util.UUID;

public interface RetrieveOrder {
    Order retrieve(UUID orderId);

    Order retrieveLastOrder(Customer customer);
}
