package com.perficient.orderapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Order {
    @NonNull
    private UUID orderId;
    @NonNull
    private UUID customerId;
    private Map<ProductItem, Integer> productItems;
    private BigDecimal totalPrice;
    private PaymentDetails paymentDetails;
    private OrderStatus orderStatus;

    public Order(Customer customer, Cart cart) {
        orderId = UUID.randomUUID();
        customerId = customer.getId();
        productItems = cart.getProducts();
        totalPrice = cart.getTotalPrice();
        orderStatus = OrderStatus.IN_PROGRESS;
    }
}
