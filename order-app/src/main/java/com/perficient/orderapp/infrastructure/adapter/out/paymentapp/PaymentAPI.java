package com.perficient.orderapp.infrastructure.adapter.out.paymentapp;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.port.PaymentPort;
import org.springframework.stereotype.Service;

@Service
public class PaymentAPI implements PaymentPort {

    @Override
    public PaymentDetails executePayment(Order order) {
        return null;
    }
}
