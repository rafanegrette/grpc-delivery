package com.perficient.orderapp.domain;

import com.perficient.orderapp.domain.excepton.InvalidProductItemException;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
public class ProductItem {

    public static final String ERROR_QUANTITY_LESS_THAN_0 = "The quantity must be greater than 0";
    @NonNull
    private UUID id;

    private String name;
    private String category;

    private int quantity;

    private BigDecimal price;
    private BigDecimal discount;

    public ProductItem(@NonNull UUID id, String name, String category, int quantity, BigDecimal price, BigDecimal discount) {

        if (quantity < 1) {
            throw new InvalidProductItemException(ERROR_QUANTITY_LESS_THAN_0);
        }

        if (price.compareTo(BigDecimal.ZERO) == -1) {
            throw new InvalidProductItemException("The price must be greater than or equal to 0");
        }

        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new InvalidProductItemException(ERROR_QUANTITY_LESS_THAN_0);
        }
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return id == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}