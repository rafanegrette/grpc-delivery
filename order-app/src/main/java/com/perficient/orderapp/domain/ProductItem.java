package com.perficient.orderapp.domain;


import com.perficient.orderapp.domain.exception.InvalidProductItemException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
public class ProductItem {

    @NonNull
    private UUID id;

    private String name;
    private String category;

    private int quantity;

    private double price;
    private double discount;

    public ProductItem(@NonNull UUID id, String name, String category, int quantity, double price, double discount) throws InvalidProductItemException {

        if (quantity < 1) {
            throw new InvalidProductItemException("The quantity must be greater than 0");
        }

        if (price < 0) {
            throw new InvalidProductItemException("The price must be greater than or equal to 0");
        }

        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public void setQuantity(int quantity) throws InvalidProductItemException {
        if (quantity < 1) {
            throw new InvalidProductItemException("The quantity must be greater than 0");
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
