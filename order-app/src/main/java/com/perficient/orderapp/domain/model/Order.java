package com.perficient.orderapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Order {
    @NonNull
    private UUID orderId;
    @NonNull
    private UUID customerId;
    private Set<ProductItem> productItems;
    private BigDecimal totalPrice;
    private PaymentDetails paymentDetails;
    private OrderStatus orderStatus;

    public void addProductItem(ProductItem productItem) {
        productItems.add(productItem);
    }
}
