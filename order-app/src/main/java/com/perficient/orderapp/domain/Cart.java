package com.perficient.orderapp.domain;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class Cart {
    private UUID id;
    private Map<ProductItem, Integer> products;
    private BigDecimal totalPrice;

    public Cart() {
        this.id = UUID.randomUUID();
        products = new HashMap<>();
        totalPrice = BigDecimal.ZERO;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(ProductItem product) {
        if (products.containsKey(product)) {
            products.computeIfPresent(product, (key, quantity) -> quantity+ 1);
        } else {
            products.put(product, 1);
        }
        totalPrice = totalPrice.add(product.getPrice());
    }

    public Map<ProductItem, Integer> getProducts() {
        return products;
    }
}
