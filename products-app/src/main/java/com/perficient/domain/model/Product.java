package com.perficient.domain.model;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table
public class Product {
    @PrimaryKey
    private UUID id;

    @Column("restaurantid")
    @CassandraType(type = CassandraType.Name.INT)
    private int restaurantId;

    @Column("name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column("category")
    @CassandraType(type = CassandraType.Name.TEXT)
    private Category category;

    @Column("available")
    @CassandraType(type = CassandraType.Name.BOOLEAN)
    private Boolean available;

    @Column("price")
    @CassandraType(type = CassandraType.Name.INT)
    private int price;

    @Column("discount")
    @CassandraType(type = CassandraType.Name.FLOAT)
    private float discount;

    public Product(UUID id, int restaurantId, String name, Category category, Boolean available, int price, float discount) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.category = category;
        this.available = available;
        this.price = price;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
