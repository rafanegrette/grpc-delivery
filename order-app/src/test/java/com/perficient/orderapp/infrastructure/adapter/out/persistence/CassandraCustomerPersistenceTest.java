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
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@DataCassandraTest(
        properties = { "spring.cassandra.schema-action=create-if-not-exists",
                "spring.cassandra.connection.connect-timeout=120s",
                "spring.cassandra.connection.init-query-timeout=60s",
                "spring.cassandra.request.timeout=60s" }
)
@ImportTestcontainers(AbstractIntegrationTest.class)
@Testcontainers(disabledWithoutDocker = true)
class CassandraCustomerPersistenceTest {

    @Autowired
    CassandraCustomerRepository cassandraCustomerRepository;

    @Autowired
    CassandraCartRepository cassandraCartRepository;

    @Test
    void given_customer_should_retrieve_success() {
        // Given
        CassandraCustomerPersistence customerPersistence = new CassandraCustomerPersistence(
                cassandraCustomerRepository,cassandraCartRepository);
        var customerGiven = CustomerMother.customer.build();
        var customerEntity = CustomerEntityMapper.INSTANCE.map(customerGiven);
        cassandraCustomerRepository.save(customerEntity);

        // When
        var customerReturned = customerPersistence.retrieve(customerGiven.getId());

        // Then
        assertEquals(customerGiven, customerReturned);
    }

    @Test
    void should_save_cart_with_products_success() {
        // Given
        CassandraCustomerPersistence customerPersistence = new CassandraCustomerPersistence(
                cassandraCustomerRepository, cassandraCartRepository);
        var cart = CartMother.cart.build();
        var product1 = ProductItemMother.product1.build();

        // When
        customerPersistence.saveCart(cart);
        var cartEntityReturned = cassandraCartRepository.findById(cart.getId());
        var cartReturned = CartEntityMapper.INSTANCE.map(cartEntityReturned.get());
        // Then
        assertEquals(cart, cartReturned);
    }
}