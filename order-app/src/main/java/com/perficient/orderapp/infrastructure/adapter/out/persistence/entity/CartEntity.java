package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("Cart")
public class CartEntity {
    @PrimaryKey
    private UUID cartId;
    private Map<ProductItemEntity, Integer> productItemEntities;
    private BigDecimal totalPrice;

}
