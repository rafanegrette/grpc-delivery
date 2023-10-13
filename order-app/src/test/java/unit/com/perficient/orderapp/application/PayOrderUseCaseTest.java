package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.port.PaymentApp;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.SaveOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PayOrderUseCaseTest {

    @Mock
    PaymentApp paymentApp;
    @Mock
    SaveOrder saveOrder;
    @Mock
    RetrieveCustomer retrieveCustomer;
    @InjectMocks
    PayOrderUseCase payOrderUseCase;

    @Test
    void payOrder_should_success() {
        // GIVEN
        var customer = CustomerMother.customer.build();
        var paymentDetails = new PaymentDetails(UUID.randomUUID(),
                LocalDateTime.now(),
                BigDecimal.valueOf(50.0));
        given(paymentApp.executePayment(any(Order.class))).willReturn(paymentDetails);
        given(retrieveCustomer.retrieve(customer.getId())).willReturn(customer);

        // WHEN
        var orderReturned = payOrderUseCase.pay(customer.getId());

        // THEN
        assertEquals(OrderStatus.PAID, orderReturned.getOrderStatus());
        assertNotNull(orderReturned.getPaymentDetails());
        assertNotNull(orderReturned.getCreationDate());
        verify(saveOrder, times(1)).save(orderReturned);
    }
}