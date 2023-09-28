package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Table("Order")
public record OrderEntity (
    @NonNull
    @PrimaryKey
    UUID orderId,
    @NonNull
    UUID customerId,
    Map<ProductItemEntity, Integer> productItemEntities,
    PaymentDetailsEntity paymentDetailsEntity,
    BigDecimal totalPrice,
    String orderStatus) {
}
