package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.RetrieveOrder;
import com.perficient.orderapp.domain.port.RetrieveProductItem;
import com.perficient.orderapp.domain.port.SaveOrder;
import com.perficient.orderapp.domain.excepton.InvalidOrderStatus;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddProductUseCase {


    RetrieveProductItem retrieveProductItem;
    RetrieveOrder retrieveOrder;
    SaveOrder saveOrder;

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
        }
    }
}
