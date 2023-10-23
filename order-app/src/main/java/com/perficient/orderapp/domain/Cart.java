package com.perficient.orderapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Cart {
    private UUID id;
    private Map<ProductItem, Integer> products;
    private BigDecimal totalPrice;

    public Cart() {
        this.id = UUID.randomUUID();
        initCart();
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void addProduct(ProductItem product) {
        initCart();
        if (products.containsKey(product)) {
            products.computeIfPresent(product, (key, quantity) -> quantity+ 1);
        } else {
            products.put(product, 1);
        }
        totalPrice = totalPrice.add(product.getPrice());
    }

    public void clean() {
        products = new HashMap<>();
        totalPrice = BigDecimal.ZERO;
    }

    private void initCart() {
        if (products == null) {
            products = new HashMap<>();
        }
        if (totalPrice == null) {
            totalPrice = BigDecimal.ZERO;
        }
    }
}
