package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.ProductResponse;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
    void orderToOrderResponse() {
        // GIVEN
        var customerId = "6d303f86-3e90-491e-b98c-96b7d32b0e9d";
        var orderId = UUID.randomUUID().toString();
        var productList = getProductList();
        var productResponseList = getProductResponseList();
        Order order = new Order(UUID.fromString(orderId),
                UUID.fromString(customerId),
                productList,
                BigDecimal.TEN,
                null,
                OrderStatus.IN_PROGRESS);
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
