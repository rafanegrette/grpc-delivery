package com.perficient.orderapp.application.port.out;

import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.domain.model.PaymentDetails;

public interface PaymentPort {
    PaymentDetails executePayment(Order order);
}
