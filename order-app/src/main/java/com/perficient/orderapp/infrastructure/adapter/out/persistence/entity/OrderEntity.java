package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("Order")
public class OrderEntity {

    @NonNull
    @PrimaryKey
    private UUID orderId;
    @NonNull
    private UUID customerId;
    //@CassandraType(type = CassandraType.Name.MAP,typeArguments = { CassandraType.Name.UDT, CassandraType.Name.INT})
    private Map<ProductItemEntity, Integer> productItemEntities;
    private PaymentDetailsEntity paymentDetailsEntity;
    private BigDecimal totalPrice;
    private String orderStatus;
}
