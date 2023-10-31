package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CartEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CustomerEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
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

import static org.junit.jupiter.api.Assertions.*;


@DataCassandraTest(
        properties = {"spring.cassandra.schema-action=create-if-not-exists",
                "spring.cassandra.connection.connect-timeout=120s",
                "spring.cassandra.connection.init-query-timeout=60s",
                "spring.cassandra.request.timeout=60s"}
)
@Import(KeyspaceTestConfiguration.class)
@Testcontainers(disabledWithoutDocker = true)
class CassandraCustomerPersistenceTest {

    @Container
    @ServiceConnection
    static CassandraContainer<?> cassandra = new CassandraContainer<>(DockerImageName.parse("cassandra"));

    @Autowired
    CassandraCustomerRepository cassandraCustomerRepository;

    @Autowired
    CassandraCartRepository cassandraCartRepository;

    @Test
    void should_save_cart_with_products_success() {
        // Given
        CassandraCustomerPersistence customerPersistence = new CassandraCustomerPersistence(
                cassandraCustomerRepository, cassandraCartRepository);
        var cart = CartMother.cart.build();

        // When
        customerPersistence.saveCart(cart);
        var cartEntityReturned = cassandraCartRepository.findById(cart.getId());
        var cartReturned = CartEntityMapper.INSTANCE.map(cartEntityReturned.get());
        // Then
        assertEquals(cart, cartReturned);
    }

    @Test
    void should_retrieve_customer_with_cart_success() {
        // Given
        CassandraCustomerPersistence customerPersistence = new CassandraCustomerPersistence(
                cassandraCustomerRepository, cassandraCartRepository);
        var customerGiven = CustomerMother.customer.build();
        customerGiven.setCart(CartMother.cart.build());
        var customerEntity = CustomerEntityMapper.INSTANCE.map(customerGiven);
        var cartEntity = CartEntityMapper.INSTANCE.map(customerGiven.getCart());
        cassandraCustomerRepository.save(customerEntity);
        cassandraCartRepository.save(cartEntity);

        // When
        var customerReturned = customerPersistence.retrieveById(customerGiven.getId());

        // Then
        assertEquals(customerGiven, customerReturned);
    }


    @Test
    void should_save_empty_cart_success() {
        // Given
        CassandraCustomerPersistence customerPersistence = new CassandraCustomerPersistence(
                cassandraCustomerRepository, cassandraCartRepository);
        var cartGiven = CartMother.cart.build();
        cartGiven.clean();

        // When
        customerPersistence.saveCart(cartGiven);

        // Then
        var customerReturned = cassandraCartRepository.findById(CartMother.ID);
        assertEquals(BigDecimal.ZERO, customerReturned.get().getTotalPrice());
        assertNull(customerReturned.get().getProductItemEntities());
    }

}