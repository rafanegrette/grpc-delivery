package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.math.BigDecimal;
import java.util.UUID;

@UserDefinedType("product_item")
public record ProductItemEntity (
    @NonNull
    @PrimaryKey
    UUID id,
    String name,
    String category,
    int quantity,
    BigDecimal price,
    BigDecimal discount) {

}
