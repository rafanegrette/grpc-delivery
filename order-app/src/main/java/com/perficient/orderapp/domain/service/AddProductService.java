package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class AddProductService implements AddProductUseCase {

    @Override
    public void addProduct(Customer customer, Product product) {

    }
}
