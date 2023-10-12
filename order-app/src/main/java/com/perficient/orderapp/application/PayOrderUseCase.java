package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.*;
import com.perficient.orderapp.domain.port.PaymentPort;
import com.perficient.orderapp.domain.port.RetrieveCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayOrderUseCase {

    private final PaymentPort paymentPort;
    private final RetrieveCustomer retrieveCustomer;

    public Order pay(UUID customerId) {
        var customer = retrieveCustomer(customerId);
        Order order = new Order(customer, customer.getCart());
        PaymentDetails paymentDetails = paymentPort.executePayment(order);
        order.setPaymentDetails(paymentDetails);
        order.setOrderStatus(OrderStatus.PAID);
        return order;
    }

    private Customer retrieveCustomer(UUID customerId) {
        return retrieveCustomer.retrieve(customerId);
    }
}
