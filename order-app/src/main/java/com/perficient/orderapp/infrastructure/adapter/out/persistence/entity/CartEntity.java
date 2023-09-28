package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Table("Cart")
public record CartEntity (
        @PrimaryKey
        UUID cartId,
        Map<ProductItemEntity, Integer> productItemEntities,
        BigDecimal totalPrice) {

}
