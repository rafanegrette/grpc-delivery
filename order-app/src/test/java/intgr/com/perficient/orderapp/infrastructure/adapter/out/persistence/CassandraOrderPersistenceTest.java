package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@DataCassandraTest(
        properties = {"spring.cassandra.schema-action=create-if-not-exists",
                "spring.cassandra.connection.connect-timeout=120s",
                "spring.cassandra.connection.init-query-timeout=60s",
                "spring.cassandra.request.timeout=60s"}
)
@Import(KeyspaceTestConfiguration.class)
@Testcontainers(disabledWithoutDocker = true)
public class CassandraOrderPersistenceTest {

    @Container
    @ServiceConnection
    static CassandraContainer<?> cassandra = new CassandraContainer<>(DockerImageName.parse("cassandra"));

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
                .paymentDetails(PaymentDetails.builder()
                        .id(UUID.randomUUID())
                        .paymentDate(LocalDateTime.now())
                        .amount(BigDecimal.valueOf(55.3))
                        .build())
                .build();

        // When
        orderPersistence.save(order);
        var orderReturned = cassandraOrderRepository.findById(orderId);
        //Order orderReturned = orderPersistence.retrieve(orderId);

        // then
        assertFalse(orderReturned.isEmpty());
        assertNotNull(orderReturned.get().getPaymentDetailsEntity());
        assertEquals(BigDecimal.valueOf(55.3), orderReturned.get().getPaymentDetailsEntity().getAmount());
        assertEquals(2, orderReturned.get().getProductItemEntities().size());
    }

}
