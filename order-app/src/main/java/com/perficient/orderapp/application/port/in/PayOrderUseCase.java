package com.perficient.orderapp.application.port.in;

import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Payment;

public interface PayOrderUseCase {

    void payOrder(Customer customer, Payment payment);

}
