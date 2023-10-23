package com.perficient.orderapp.infrastructure.adapter.out.paymentapp;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.port.PaymentApp;
import com.perficient.proto.invoice.Invoice;
import com.perficient.proto.invoice.InvoiceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentAPI implements PaymentApp {

    @Override
    public PaymentDetails executePayment(Order order) {
        InvoiceResponse invoiceResponse = InvoiceResponse.newBuilder()
                .setInvoice(Invoice.newBuilder()
                        .setInvoiceId(UUID.randomUUID().toString())
                        .setValue(order.getTotalPrice().doubleValue())
                        .setClient(order.getCustomerId().toString())
                        .setOrderId(order.getOrderId().toString())
                        .build())
                .build();
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .paymentDate(LocalDateTime.now())
                .amount(order.getTotalPrice())
                .id(UUID.fromString(invoiceResponse.getInvoice().getInvoiceId()))
                .build();
        return paymentDetails;
    }
}
