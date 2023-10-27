package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.google.protobuf.Timestamp;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentDetail;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.ProductResponse;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderMapperTest {

    public static final String PRODUCT_NAME_1 = "Subway with jam";
    public static final String PRODUCT_NAME_2 = "Hamburger Avenger";
    final String PRODUCT_ID_1 = "ca920c2f-3e90-491e-b98c-96b7d32b0e9c";
    final String PRODUCT_ID_2 = "7c303f86-19e7-4257-b216-9e24d56972eb";

    @Test
    void orderToOrderResponse_without_paymentdetails() {
        // GIVEN
        var customerId = "6d303f86-3e90-491e-b98c-96b7d32b0e9d";
        var orderId = UUID.randomUUID().toString();
        var productList = getProductList();
        var productResponseList = getProductResponseList();
        Order order = new Order(UUID.fromString(orderId),
                UUID.fromString(customerId),
                productList,
                BigDecimal.TEN,
                PaymentDetails.builder()
                        .amount(BigDecimal.TEN)
                        .paymentDate(LocalDateTime.now())
                        .build(),
                OrderStatus.IN_PROGRESS,
                LocalDateTime.now());
        OrderResponse orderResponseExpected = OrderResponse.newBuilder()
                .setOrderId(orderId)
                .setCustomerId(customerId)
                .addAllProducts(productResponseList)
                .setOrderStatus("IN_PROGRESS")
                .build();

        // WHEN
        var orderResponseReturned = OrderMapper.INSTANCE.map(order);
        // THEN

        assertEquals(orderResponseExpected.getOrderId(), orderResponseReturned.getOrderId());
        assertEquals(orderResponseExpected.getOrderStatus(), orderResponseReturned.getOrderStatus());
        assertEquals(orderResponseExpected.getCustomerId(), orderResponseReturned.getCustomerId());
        orderResponseExpected.getProductsList().forEach(productResponseExpected ->
                assertTrue(orderResponseReturned.getProductsList().contains(productResponseExpected))
        );
    }

    @Test
    void orderToOrderResponse_with_paymentdetails() {
        // GIVEN
        var customerId = "6d303f86-3e90-491e-b98c-96b7d32b0e9d";
        var orderId = UUID.randomUUID().toString();
        var paymentDetailID = UUID.randomUUID();
        var paymentDate = LocalDateTime.now();
        var instant = paymentDate.atZone(ZoneId.systemDefault()).toInstant();
        var paymentDetailGrpcExpected = PaymentDetail.newBuilder()
                .setId(paymentDetailID.toString())
                .setAmount(BigDecimal.TEN.doubleValue())
                .setDate(Timestamp.newBuilder()
                        .setSeconds(instant.getEpochSecond())
                        .setNanos(instant.getNano())
                        .build());
        Order order = new Order(UUID.fromString(orderId),
                UUID.fromString(customerId),
                new HashMap<>(),
                BigDecimal.TEN,
                PaymentDetails.builder()
                        .id(paymentDetailID)
                        .paymentDate(paymentDate)
                        .amount(BigDecimal.TEN)
                        .build(),
                OrderStatus.IN_PROGRESS,
                LocalDateTime.now());
        OrderResponse orderResponseExpected = OrderResponse.newBuilder()
                .setOrderId(orderId)
                .setCustomerId(customerId)
                .setPaymentDetail(paymentDetailGrpcExpected)
                .setOrderStatus("IN_PROGRESS")
                .build();

        // WHEN
        var orderResponseReturned = OrderMapper.INSTANCE.map(order);
        // THEN

        assertEquals(orderResponseExpected.getOrderId(), orderResponseReturned.getOrderId());
        assertEquals(orderResponseExpected.getOrderStatus(), orderResponseReturned.getOrderStatus());
        assertEquals(orderResponseExpected.getCustomerId(), orderResponseReturned.getCustomerId());
        assertEquals(paymentDetailGrpcExpected.getAmount(), orderResponseReturned.getPaymentDetail().getAmount());
        assertEquals(paymentDetailGrpcExpected.getDate(), orderResponseReturned.getPaymentDetail().getDate());
    }

    private Map<ProductItem, Integer> getProductList() {
        var product1 = new ProductItem(UUID.fromString(PRODUCT_ID_1),
                PRODUCT_NAME_1,
                "Vegetables",
                BigDecimal.TEN,
                BigDecimal.ZERO);
        var product2 = new ProductItem(UUID.fromString(PRODUCT_ID_2),
                PRODUCT_NAME_2,
                "Vegetables",
                BigDecimal.valueOf(5.5),
                BigDecimal.ZERO);
        return Map.of(product1, 2, product2, 1);
    }

    private List<ProductResponse> getProductResponseList() {
        var productResponse1 = ProductResponse.newBuilder()
                .setId(PRODUCT_ID_1)
                .setQuantity(2)
                .setName(PRODUCT_NAME_1)
                .setPrice(10.0)
                .build();
        var productResponse2 = ProductResponse.newBuilder()
                .setId(PRODUCT_ID_2)
                .setQuantity(1)
                .setName(PRODUCT_NAME_2)
                .setPrice(5.5)
                .build();
        return List.of(productResponse1, productResponse2);
    }
}
