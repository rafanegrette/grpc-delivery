package com.perficient.orderapp.domain.port;

import com.perficient.orderapp.domain.Customer;

import java.util.UUID;

public interface RetrieveCustomer {
    Customer retrieve(UUID customerId);
}
