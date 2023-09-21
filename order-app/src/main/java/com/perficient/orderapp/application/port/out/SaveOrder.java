package com.perficient.orderapp.application.port.out;

import com.perficient.orderapp.domain.model.Order;

public interface SaveOrder {

    void save(Order order);
}
