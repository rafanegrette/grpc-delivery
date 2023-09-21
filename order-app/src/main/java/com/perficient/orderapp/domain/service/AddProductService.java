package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.out.RetrieveOrder;
import com.perficient.orderapp.application.port.out.RetrieveProductItem;
import com.perficient.orderapp.application.port.out.SaveOrder;
import com.perficient.orderapp.domain.excepton.InvalidOrderStatus;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProductService implements AddProductUseCase {


    RetrieveProductItem retrieveProductItem;
    RetrieveOrder retrieveOrder;
    SaveOrder saveOrder;

    @Override
    public void addProductToOrder(Order order, UUID productItemId, int quantity) {
        verifyOrder(order);
        var productItem = retrieveProductItem.retrieve(productItemId);
        order.addProductItem(productItem);
        saveOrder.save(order);
    }

    private void verifyOrder(Order order) {
        var orderRetrieved = retrieveOrder.retrieve(order.getOrderId());
        if (!OrderStatus.IN_PROGRESS.equals(orderRetrieved.getOrderStatus())) {
            throw new InvalidOrderStatus("Can't add more products the order is in status: " + order.getOrderStatus());
        };
    }
}
