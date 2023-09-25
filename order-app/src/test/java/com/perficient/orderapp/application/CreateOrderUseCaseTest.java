package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.SaveOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    SaveOrder saveOrder;

    @InjectMocks
    CreateOrderUseCase createOrderUseCase;

    @Test
    void create_should_success() {
        // GIVEN
        var customerId = UUID.randomUUID();
        // WHEN
        var order = createOrderUseCase.create(customerId);
        // THEN
        assertNotNull(order);
        assertEquals(0, order.getProductItems().size());
    }
}