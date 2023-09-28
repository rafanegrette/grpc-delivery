package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.PaymentDetailsEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.ProductItemEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderEntityMapperTest {

    @Test
    void test_order_to_order_entity() {
        var orderId = UUID.randomUUID();
        var customerId = UUID.randomUUID();
        var paymentDetailId = UUID.randomUUID();
        var paymentDate = LocalDateTime.now();
        var productId = UUID.randomUUID();
        var productItem = ProductItemMother.product1
                .id(productId)
                .build();

        var productItemEntity = new ProductItemEntity(
                productId,
                productItem.getName(),
                productItem.getCategory(),
                productItem.getPrice(),
                productItem.getDiscount()
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
                .productItems(Map.of(productItem, 1))
                .build();
        var paymentDetailEntity = new PaymentDetailsEntity(
                paymentDetailId,
                paymentDate,
                BigDecimal.TEN
        );
        var orderEntity = new OrderEntity(
                orderId,
                customerId,
                Map.of(productItemEntity, 1),
                paymentDetailEntity,
                BigDecimal.TEN,
                "IN_PROGRESS");

        // When
        var orderEntityReturned = OrderEntityMapper.INSTANCE.map(order);

        // Then
        assertEquals(orderEntity, orderEntityReturned);
    }
}
