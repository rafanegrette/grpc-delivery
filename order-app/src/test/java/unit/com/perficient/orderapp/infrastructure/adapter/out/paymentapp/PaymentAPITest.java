package com.perficient.orderapp.infrastructure.adapter.out.paymentapp;

import com.perficient.orderapp.domain.mother.OrderMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentAPITest {

    @InjectMocks
    PaymentAPI paymentAPI;

    @Test
    void executePayment() {

        // Given
        var order = OrderMother.order.build();

        // When
        var paymentDetails = paymentAPI.executePayment(order);

        // Then

        assertNotNull(paymentDetails);
    }
}