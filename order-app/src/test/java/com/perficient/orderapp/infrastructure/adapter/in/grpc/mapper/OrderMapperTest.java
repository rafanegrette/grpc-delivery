package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductResponse;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.OrderStatus;
import com.perficient.orderapp.domain.model.ProductItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMapperTest {

    public static final String PRODUCT_NAME_1 = "Subway with jam";
    public static final String PRODUCT_NAME_2 = "Hamburger Avenger";
    final String PRODUCT_ID_1 = "ca920c2f-3e90-491e-b98c-96b7d32b0e9c";
    final String PRODUCT_ID_2 = "7c303f86-19e7-4257-b216-9e24d56972eb";

    @Test
    void orderToOrderResponse() {
        // GIVEN
        String orderId = UUID.randomUUID().toString();
        var productList = getProductList();
        var productResponseList = getProductResponseList();
        Order order = new Order(UUID.fromString(orderId),
                new Customer(UUID.fromString("6d303f86-3e90-491e-b98c-96b7d32b0e9d"), "guy1"),
                productList,
                BigDecimal.TEN,
                null,
                OrderStatus.IN_PROGRESS);
        OrderResponse orderResponseExpected = OrderResponse.newBuilder()
                .setOrderId(orderId)
                .setCustomer("guy1")
                .addAllProducts(productResponseList)
                .setOrderStatus("IN_PROGRESS")
                .build();

        // WHEN
        var orderResponseReturned = OrderMapper.INSTANCE.map(order);
        // THEN

        assertEquals(orderResponseExpected, orderResponseReturned);
    }

    private List<ProductItem> getProductList() {
        var product1 = new ProductItem(UUID.fromString(PRODUCT_ID_1),
                PRODUCT_NAME_1,
                "Vegetables",
                1,
                BigDecimal.TEN,
                BigDecimal.ZERO);
        var product2 = new ProductItem(UUID.fromString(PRODUCT_ID_2),
                PRODUCT_NAME_2,
                "Vegetables",
                2,
                BigDecimal.valueOf(5.5),
                BigDecimal.ZERO);
        return List.of(product1, product2);
    }

    private List<ProductResponse> getProductResponseList() {
        var productResponse1 = ProductResponse.newBuilder()
                .setId(PRODUCT_ID_1)
                .setQuantity(1)
                .setName(PRODUCT_NAME_1)
                .build();
        var productResponse2 = ProductResponse.newBuilder()
                .setId(PRODUCT_ID_2)
                .setQuantity(2)
                .setName(PRODUCT_NAME_2)
                .build();
        return List.of(productResponse1, productResponse2);
    }
}
