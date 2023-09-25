package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.RetrieveOrder;
import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.domain.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RetrieveOrderUseCaseTest {

    @Mock
    RetrieveOrder retrieveOrder;

    @InjectMocks
    RetrieveOrderUseCase retrieveOrderUseCase;

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
        var orderReturned = retrieveOrderUseCase.retrieveCurrentOrder(currentCustomer);

        // THEN
        assertNotNull(orderReturned);
    }
}