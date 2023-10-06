package com.perficient.orderapp.domain.port;

import com.perficient.orderapp.domain.Order;

public interface SaveOrder {

    void save(Order order);
}
