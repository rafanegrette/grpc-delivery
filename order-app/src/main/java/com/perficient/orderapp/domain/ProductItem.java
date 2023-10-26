package com.perficient.orderapp.domain;

import com.perficient.orderapp.domain.excepton.InvalidProductItemException;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
public class ProductItem {

    public static final String ERROR_PRICE_LESS_THAN_0 = "The price must be greater than or equal to 0";
    @NonNull
    private UUID id;

    private String name;
    private String category;

    private BigDecimal price;
    private BigDecimal discount;

    public ProductItem(@NonNull UUID id, String name, String category, BigDecimal price, BigDecimal discount) {

        if (price.compareTo(BigDecimal.ZERO) == -1) {
            throw new InvalidProductItemException(ERROR_PRICE_LESS_THAN_0);
        }

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) == -1) {
            throw new InvalidProductItemException(ERROR_PRICE_LESS_THAN_0);
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return id.toString().equals(that.getId().toString());
    }


    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}