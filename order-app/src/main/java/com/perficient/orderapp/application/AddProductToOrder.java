package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.port.ProductPort;

import java.util.UUID;

public class AddProductToOrder {

    private ProductPort productPort;

    public AddProductToOrder(ProductPort productPort) {
        this.productPort = productPort;
    }

    void addProductToOrder(Order order, UUID productId) {
        ProductItem productItem = productPort.getProductById(productId);
        order.addProductItem(productItem);
    }

}
