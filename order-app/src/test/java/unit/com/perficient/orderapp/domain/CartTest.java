package com.perficient.orderapp.domain;

import com.perficient.orderapp.domain.mother.ProductItemMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    @DisplayName("When add products the total price \n" +
            "should match the price x quantity of products")
    void add_product_total_price_success() {
        // GIVEN
        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();
        var cart = new Cart();
        // WHEN
        cart.addProduct(product1);
        cart.addProduct(product2);
        var total = cart.getTotalPrice();
        // THEN
        assertEquals(ProductItemMother.TOTAL_PRICE, total);
    }

    @Test
    @DisplayName("Empty Cart retrieve from db  \n" +
            "should add products success")
    void add_product_empty_cart_success() {
        // GIVEN
        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();
        var cart = new Cart(UUID.randomUUID(), null, null);
        // WHEN
        cart.addProduct(product1);
        cart.addProduct(product2);
        var total = cart.getTotalPrice();
        // THEN
        assertEquals(ProductItemMother.TOTAL_PRICE, total);
    }
}
