package com.perficient.orderapp.domain.mother;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.ProductItem;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class CartMother {
    public static final UUID ID = UUID.randomUUID();
    public static CartBuilder cart = new CartBuilder()
            .id(ID)
            .products(Map.of(ProductItemMother.product1.build(), 1,
                    ProductItemMother.product2.build(), 1))
            .totalPrice(BigDecimal.valueOf(14.3));

    public static class CartBuilder {
        private UUID id;
        private Map<ProductItem, Integer> products;
        private BigDecimal totalPrice;

        public CartBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CartBuilder products(Map<ProductItem, Integer> products) {
            this.products = products;
            return this;
        }

        public CartBuilder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Cart build() {
            return new Cart(id, products, totalPrice);
        }
    }
}
