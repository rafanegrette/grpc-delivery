package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CassandraOrderPersistenceTest  extends AbstractIntegrationTest {
    final String PRODUCT_ID_1 = "ca920c2f-3e90-491e-b98c-96b7d32b0e9c";
    final String PRODUCT_ID_2 = "7c303f86-19e7-4257-b216-9e24d56972eb";
    final String PRODUCT_NAME_1 = "Subway with jam";
    final String PRODUCT_NAME_2 = "Hamburger Avenger";

    @Autowired
    CassandraOrderRepository cassandraOrderRepository;

    @Test
    void save_order_should_success() {
        CassandraOrderPersistence orderPersistence = new CassandraOrderPersistence(cassandraOrderRepository);
        var products = getProducts();
        var orderId = UUID.randomUUID();
        var order = Order.builder()
                .orderId(orderId)
                .customerId(UUID.randomUUID())
                .orderStatus(OrderStatus.PAID)
                .productItems(products)
                .build();
        orderPersistence.save(order);
        var orderReturned = cassandraOrderRepository.findById(orderId);
        //Order orderReturned = orderPersistence.retrieve(orderId);

        assertFalse(orderReturned.isEmpty());
        assertEquals(2, orderReturned.get().productItemEntities().size());
    }

    private Set<ProductItem> getProducts() {
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
        return Set.of(product1, product2);
    }

}
