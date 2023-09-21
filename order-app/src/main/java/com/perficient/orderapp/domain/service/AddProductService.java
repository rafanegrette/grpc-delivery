package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.ProductItem;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProductService implements AddProductUseCase {


    @Override
    public void addProductToOrder(Order order, UUID productItemId, int quantity) {

    }
}
