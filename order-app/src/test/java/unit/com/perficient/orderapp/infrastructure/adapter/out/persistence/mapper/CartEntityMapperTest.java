package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.ProductItemEntity;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartEntityMapperTest {

    @Test
    void domain_to_entity_test() {
        // Given
        var cart = CartMother.cart.build();
        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();
        var productItemEntity1 = new ProductItemEntity(
                product1.getId(),
                product1.getName(),
                product1.getCategory(),
                product1.getPrice(),
                product1.getDiscount()
        );
        var productItemEntity2 = new ProductItemEntity(
                product2.getId(),
                product2.getName(),
                product2.getCategory(),
                product2.getPrice(),
                product2.getDiscount()
        );
        var cartEntityGiven = new CartEntity(
                cart.getId(),
                Map.of(productItemEntity1, 1, productItemEntity2, 1),
                cart.getTotalPrice()
        );
        // When
        var cartEntityReturned = CartEntityMapper.INSTANCE.map(cart);
        // Then
        assertEquals(cartEntityGiven, cartEntityReturned);
    }

    @Test
    void entity_to_domain_test() {
        // Given
        var cart = CartMother.cart.build();
        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();
        var productItemEntity1 = new ProductItemEntity(
                product1.getId(),
                product1.getName(),
                product1.getCategory(),
                product1.getPrice(),
                product1.getDiscount()
        );
        var productItemEntity2 = new ProductItemEntity(
                product2.getId(),
                product2.getName(),
                product2.getCategory(),
                product2.getPrice(),
                product2.getDiscount()
        );
        var cartEntity = new CartEntity(
                cart.getId(),
                Map.of(productItemEntity1, 1, productItemEntity2, 1),
                cart.getTotalPrice()
        );
        // When
        var cartReturned = CartEntityMapper.INSTANCE.map(cartEntity);
        // Then
        assertEquals(cart, cartReturned);
    }
}
