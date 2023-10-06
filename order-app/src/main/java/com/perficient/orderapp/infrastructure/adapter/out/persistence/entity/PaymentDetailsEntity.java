package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@UserDefinedType("payment_details")
public record PaymentDetailsEntity (
        @PrimaryKey
        UUID id,
        LocalDateTime paymentDate,
        BigDecimal amount) {

}
