package com.perficient.orderapp.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Order {

    @NonNull
    private UUID orderId;

    @NonNull
    private UUID customerId;

    private Set<ProductItem> productItems;

    private double price;
    private PaymentDetails paymentDetails;
    private OrderStatus orderStatus;

    public void addProductItem(ProductItem item) {
        productItems.add(item);
    }


}
