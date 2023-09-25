package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.PaymentDetailsEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.ProductItemEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderEntityMapperTest {

    private final String PRODUCT_NAME = "Potatoes";
    private final String CATEGORY = "Fast Food";

    @Test
    void test_order_to_order_entity() {
        var orderId = UUID.randomUUID();
        var customerId = UUID.randomUUID();
        var paymentDetailId = UUID.randomUUID();
        var paymentDate = LocalDateTime.now();
        var productId = UUID.randomUUID();
        var productItem = new ProductItem(
                productId,
                PRODUCT_NAME,
                CATEGORY,
                3,
                BigDecimal.TEN,
                BigDecimal.ZERO
        );
        var productItemEntity = new ProductItemEntity(
                productId,
                PRODUCT_NAME,
                CATEGORY,
                3,
                BigDecimal.TEN,
                BigDecimal.ZERO
        );
        var paymentDetails = PaymentDetails.builder()
                .paymentDate(paymentDate)
                .amount(BigDecimal.TEN)
                .id(paymentDetailId)
                .build();
        var order = Order.builder()
                .orderId(orderId)
                .customerId(customerId)
                .orderStatus(OrderStatus.IN_PROGRESS)
                .totalPrice(BigDecimal.TEN)
                .paymentDetails(paymentDetails)
                .productItems(Set.of(productItem))
                .build();
        var paymentDetailEntity = new PaymentDetailsEntity(
                paymentDetailId,
                paymentDate,
                BigDecimal.TEN
        );
        var orderEntity = new OrderEntity(
                orderId,
                customerId,
                Set.of(productItemEntity),
                paymentDetailEntity,
                BigDecimal.TEN,
                "IN_PROGRESS");

        //order.
        var orderEntityReturned = OrderEntityMapper.INSTANCE.map(order);

        assertEquals(orderEntity, orderEntityReturned);
    }
}
