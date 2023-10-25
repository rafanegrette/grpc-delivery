package com.perficient.orderapp.infrastructure.adapter.out.paymentapp;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.excepton.UnavailablePaymentException;
import com.perficient.orderapp.domain.port.PaymentApp;
import com.perficient.proto.invoice.Invoice;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentAPI implements PaymentApp {

    private final InvoiceServiceGrpc.InvoiceServiceBlockingStub invoiceServiceGrpcApi;

    @Override
    public PaymentDetails executePayment(Order order) {

        InvoiceRequest invoiceRequest = InvoiceRequest.newBuilder()
                        .setInvoice(Invoice.newBuilder()
                                .setValue(order.getTotalPrice().doubleValue())
                                .setClientId(order.getCustomerId().toString())
                                .setOrderId(order.getOrderId().toString())
                                .build())
                                .build();
        try {
            InvoiceResponse invoiceResponse = invoiceServiceGrpcApi.payment(invoiceRequest);

            return PaymentDetails.builder()
                    .paymentDate(LocalDateTime.now())
                    .amount(order.getTotalPrice())
                    .id(UUID.nameUUIDFromBytes(invoiceResponse.getInvoice().getInvoiceId().getBytes()))
                    .build();
        } catch (StatusRuntimeException ex) {
            throw new UnavailablePaymentException();
        }

    }
}
