package com.perficient.orderapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Builder
public class PaymentDetails {

    @NonNull
    private UUID id;

    @NonNull
    private Date paymentDate;
    @NonNull
    private double amount;

}
