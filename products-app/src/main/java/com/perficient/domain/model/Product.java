package com.perficient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {
    private UUID id;
    private int restaurantId;
    private String name;
    private Category category;
    private Boolean available;
    private int price;
    private float discount;
}
