package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.SaveCustomerCart;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CartEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.CustomerEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CassandraCustomerPersistence implements RetrieveCustomer, SaveCustomerCart {

    private final CassandraCustomerRepository cassandraCustomerRepository;
    private final CassandraCartRepository cassandraCartRepository;

    @Override
    public Customer retrieve(UUID customerId) {
        var customerEntity = cassandraCustomerRepository.findById(customerId).orElseThrow();
        return CustomerEntityMapper.INSTANCE.map(customerEntity);
    }

    // TODO saveCart should be in his own class

    @Override
    public void saveCart(Cart cart) {
        var cartEntity = CartEntityMapper.INSTANCE.map(cart);
        cassandraCartRepository.save(cartEntity);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }

    // TODO remove this initializer
    @Bean
    public CommandLineRunner clr() {
        return args -> {
            UUID idUser = UUID.fromString("63d89df6-32bb-47ea-861c-a72605a833fe");
            UUID idCart = UUID.fromString("7834ec29-a784-40a7-b875-8b671a72c245");;
            CustomerEntity customerEntity = new CustomerEntity(
                    idUser,
                    "TestUser",
                    "742 Evergreen Terrace",
                    idCart);
            cassandraCustomerRepository.save(customerEntity);

            CartEntity cartEntity = new CartEntity(
                    idCart,
                    new HashMap<>(),
                    BigDecimal.ZERO);
            cassandraCartRepository.save(cartEntity);
        };
    }
}
