package com.perficient.orderapp.domain.port;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;

public interface PaymentPort {
    PaymentDetails executePayment(Order order);
}
