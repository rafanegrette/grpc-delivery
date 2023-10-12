package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UserDefinedType("product_item")
public class ProductItemEntity {
    @NonNull
    @PrimaryKey
    private UUID id;
    private String name;
    private String category;
    private BigDecimal price;
    private BigDecimal discount;

}
