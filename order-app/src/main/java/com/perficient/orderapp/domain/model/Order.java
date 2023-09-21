package com.perficient.orderapp.domain.model;

import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record Order (@NonNull UUID orderId,
                     @NonNull Customer customer,
                     List<ProductItem> productItems,
                     BigDecimal totalPrice,
                     PaymentDetails paymentDetails,
                     OrderStatus orderStatus) {


}
