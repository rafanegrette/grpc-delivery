package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.SaveCustomerCart;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CartEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CustomerEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CassandraCustomerPersistence implements RetrieveCustomer, SaveCustomerCart {

    private final CassandraCustomerRepository cassandraCustomerRepository;
    private final CassandraCartRepository cassandraCartRepository;

    @Override
    public Customer retrieveById(UUID customerId) {
        var customerEntity = cassandraCustomerRepository.findById(customerId).orElseThrow();
        var cartEntity = cassandraCartRepository.findById(customerEntity.getCartId());
        var cart = CartEntityMapper.INSTANCE.map(cartEntity.orElseGet(CartEntity::new));
        var customer = CustomerEntityMapper.INSTANCE.map(customerEntity);
        customer.setCart(cart);
        return customer;
    }

    // TODO saveCart should be in his own class

    @Override
    public void saveCart(Cart cart) {
        var cartEntity = CartEntityMapper.INSTANCE.map(cart);
        cassandraCartRepository.save(cartEntity);
    }

}
