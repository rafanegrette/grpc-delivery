package com.perficient.orderapp.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentDetails (
        UUID id,
        LocalDateTime paymentDate,
        BigDecimal amount) {

}
