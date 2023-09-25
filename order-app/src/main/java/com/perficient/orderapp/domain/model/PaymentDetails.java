package com.perficient.orderapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class PaymentDetails {
        private UUID id;
        private LocalDateTime paymentDate;
        private BigDecimal amount;

}
