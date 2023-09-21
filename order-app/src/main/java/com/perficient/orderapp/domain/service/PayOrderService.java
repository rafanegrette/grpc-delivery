package com.perficient.orderapp.domain.service;

import com.perficient.orderapp.application.port.in.PayOrderUseCase;
import com.perficient.orderapp.application.port.out.PaymentPort;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.OrderStatus;
import com.perficient.orderapp.domain.model.PaymentDetails;

public class PayOrderService implements PayOrderUseCase {

    PaymentPort paymentPort;

    @Override
    public void payOrder(Order order) {
        PaymentDetails paymentDetails = paymentPort.executePayment(order);
        order.setPaymentDetails(paymentDetails);
        order.setOrderStatus(OrderStatus.PAID);
    }
}
