package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.out.RetrieveOrder;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RetrieveOrderServiceTest {

    @Mock
    RetrieveOrder retrieveOrder;

    @InjectMocks
    RetrieveOrderService retrieveOrderService;

    @Test
    void retrieveCurrentOrder() {
        // GIVEN
        var order = Order.builder()
                .orderId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .build();
        var currentCustomer = new Customer(UUID.randomUUID(), "guy1");
        given(retrieveOrder.retrieveLastOrder(currentCustomer)).willReturn(order);
        // WHEN
        var orderReturned = retrieveOrderService.retrieveCurrentOrder(currentCustomer);

        // THEN
        assertNotNull(orderReturned);
    }
}