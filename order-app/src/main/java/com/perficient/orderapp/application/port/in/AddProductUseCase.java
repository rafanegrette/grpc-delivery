package com.perficient.orderapp.application.port.in;

import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;

import java.util.UUID;

public interface AddProductUseCase {

    void addProductToOrder(Order order, UUID productItemId, int quantity);
}
