package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.out.SaveOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderServiceTest {

    @Mock
    SaveOrder saveOrder;

    @InjectMocks
    CreateOrderService createOrderService;

    @Test
    void create_should_success() {
        // GIVEN
        var customerId = UUID.randomUUID();
        // WHEN
        var order = createOrderService.create(customerId);
        // THEN
        assertNotNull(order);
        assertEquals(0, order.getProductItems().size());
    }
}