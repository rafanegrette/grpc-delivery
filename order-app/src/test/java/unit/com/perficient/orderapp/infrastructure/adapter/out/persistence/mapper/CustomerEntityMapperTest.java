package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerEntityMapperTest {

    @Test
    void domain_to_entity_test() {
        // Given
        var customer = CustomerMother.customer.build();
        var customerEntity = new CustomerEntity(customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getCart().getId());
        // When
        var customerEntityReturned = CustomerEntityMapper.INSTANCE.map(customer);
        // Then

        assertEquals(customerEntity, customerEntityReturned);
    }

    @Test
    void entity_to_domain_test() {
        // Given
        var customer = CustomerMother.customer.build();
        var customerEntity = new CustomerEntity(customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getCart().getId());
        // When
        var customerReturned = CustomerEntityMapper.INSTANCE.map(customerEntity);
        // Then

        assertEquals(customer, customerReturned);
    }
}
