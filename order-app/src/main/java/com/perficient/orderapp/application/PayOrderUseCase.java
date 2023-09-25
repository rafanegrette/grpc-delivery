package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.PaymentPort;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.PaymentDetails;

public class PayOrderUseCase {

    PaymentPort paymentPort;

    public void payOrder(Order order) {
        PaymentDetails paymentDetails = paymentPort.executePayment(order);
        order.setPaymentDetails(paymentDetails);
        order.setOrderStatus(OrderStatus.PAID);
    }
}
