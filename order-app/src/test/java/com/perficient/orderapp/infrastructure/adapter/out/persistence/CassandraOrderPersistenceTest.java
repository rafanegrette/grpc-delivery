package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CassandraOrderPersistenceTest  extends AbstractIntegrationTest {

    @Autowired
    CassandraOrderRepository cassandraOrderRepository;

    @Test
    void save_order_should_success() {
        // Given
        CassandraOrderPersistence orderPersistence = new CassandraOrderPersistence(cassandraOrderRepository);
        var products = Map.of(ProductItemMother.product1.build(), 1,
                ProductItemMother.product2.build(), 1);

        var orderId = UUID.randomUUID();
        var order = Order.builder()
                .orderId(orderId)
                .customerId(UUID.randomUUID())
                .orderStatus(OrderStatus.PAID)
                .productItems(products)
                .build();

        // When
        orderPersistence.save(order);
        var orderReturned = cassandraOrderRepository.findById(orderId);
        //Order orderReturned = orderPersistence.retrieve(orderId);

        // then
        assertFalse(orderReturned.isEmpty());
        assertEquals(2, orderReturned.get().productItemEntities().size());
    }


}
