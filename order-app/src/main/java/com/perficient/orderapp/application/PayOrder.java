package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.port.PaymentPort;

public class PayOrder {

    private PaymentPort paymentPort;

    public PayOrder(PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    public void payOrder(Order order) {
        PaymentDetails paymentDetails = paymentPort.executePayment(order);
        order.setPaymentDetails(paymentDetails);
        order.setOrderStatus(OrderStatus.PAID);
    }
}
