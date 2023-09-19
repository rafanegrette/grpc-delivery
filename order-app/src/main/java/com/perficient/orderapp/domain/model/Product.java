package com.perficient.orderapp.domain.model;

import java.util.UUID;

public record Product (UUID productId,
                       String name,
                       int quantity){
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (quantity != product.quantity) return false;
        return productId.equals(product.productId);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
