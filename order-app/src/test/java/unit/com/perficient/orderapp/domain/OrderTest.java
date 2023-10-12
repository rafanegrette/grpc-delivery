package com.perficient.orderapp.domain;

import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.CustomerMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void create_order_should_have_right_totals() {
        // Given
        var cart = CartMother.cart.build();
        var customer = CustomerMother.customer.build();
        // When

        var order = new Order(customer, cart);
        // Then

        assertEquals(cart.getTotalPrice(), order.getTotalPrice());
        assertEquals(cart.getProducts().size(), order.getProductItems().size());
    }
}
