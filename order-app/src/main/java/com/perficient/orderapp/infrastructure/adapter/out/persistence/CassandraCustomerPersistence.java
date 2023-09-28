package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import com.perficient.orderapp.domain.port.SaveCustomerCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CassandraCustomerPersistence implements RetrieveCustomer, SaveCustomerCart {
    @Override
    public Customer retrieve(UUID customerId) {
        return null;
    }

    @Override
    public void save(Cart cart) {

    }
}
