package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@UserDefinedType("payment_details")
public class PaymentDetailsEntity {
        @PrimaryKey
        private UUID id;
        private LocalDateTime paymentDate;
        private BigDecimal amount;

}
