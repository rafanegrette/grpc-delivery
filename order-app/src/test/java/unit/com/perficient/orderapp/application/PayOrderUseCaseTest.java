package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.excepton.EmptyCartException;
import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.port.PaymentApp;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.SaveCustomerCart;
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
    @Mock
    SaveCustomerCart saveCustomerCart;
    @InjectMocks
    PayOrderUseCase payOrderUseCase;

    @Test
    void payOrder_should_success() throws InterruptedException {
        // GIVEN
        var customer = CustomerMother.customer.build();
        customer.setCart(CartMother.cart.build());
        var paymentDetails = new PaymentDetails(UUID.randomUUID(),
                LocalDateTime.now(),
                BigDecimal.valueOf(50.0));
        given(paymentApp.executePayment(any(Order.class))).willReturn(paymentDetails);
        given(retrieveCustomer.retrieveById(customer.getId())).willReturn(customer);

        // WHEN
        var orderReturned = payOrderUseCase.pay(customer.getId());

        // THEN
        assertEquals(OrderStatus.PAID, orderReturned.getOrderStatus());
        assertNotNull(orderReturned.getPaymentDetails());
        assertNotNull(orderReturned.getCreationDate());
        verify(saveOrder, times(1)).save(orderReturned);
        verify(saveCustomerCart, times(1)).saveCart(customer.getCart());
        assertEquals(0, customer.getCart().getProducts().size());
        assertEquals(BigDecimal.ZERO, customer.getCart().getTotalPrice());
    }

    @Test
    void payOrder_without_products_should_throw_empty_cart() {
        // GIVEN
        var customer = CustomerMother.customer.build();
        customer.setCart(new Cart());
        given(retrieveCustomer.retrieveById(customer.getId())).willReturn(customer);

        // WHEN
        assertThrows(EmptyCartException.class, () ->
                payOrderUseCase.pay(customer.getId())
        );

        // THEN
    }
}