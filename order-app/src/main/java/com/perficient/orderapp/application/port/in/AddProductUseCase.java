package com.perficient.orderapp.application.port.in;

import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Product;

public interface AddProductUseCase {

    void addProduct(Customer customer, Product product);
}
