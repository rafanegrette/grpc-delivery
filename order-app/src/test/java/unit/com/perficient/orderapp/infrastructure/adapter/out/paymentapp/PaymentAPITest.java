package com.perficient.orderapp.infrastructure.adapter.out.paymentapp;

import com.perficient.orderapp.domain.excepton.UnavailablePaymentException;
import com.perficient.orderapp.domain.mother.OrderMother;
import com.perficient.proto.invoice.Invoice;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentAPITest {

    @Mock
    InvoiceServiceGrpc.InvoiceServiceBlockingStub invoiceGrpcAPI;
    @InjectMocks
    PaymentAPI paymentAPI;

    @Test
    void executePayment() {

        // Given
        var order = OrderMother.order.build();
        Invoice invoice = Invoice.newBuilder()
                .setClientId(order.getCustomerId().toString())
                .setOrderId(order.getOrderId().toString())
                .setValue(order.getTotalPrice().doubleValue())
                .build();
        Invoice invoiceResponse = Invoice.newBuilder()
                .setInvoiceId(UUID.randomUUID().toString())
                .setClientId(order.getCustomerId().toString())
                .setOrderId(order.getOrderId().toString())
                .setValue(order.getTotalPrice().doubleValue())
                .build();
        InvoiceRequest invoiceRequest = InvoiceRequest.newBuilder()
                .setInvoice(invoice)
                .build();
        InvoiceResponse invoiceResponseWrapper = InvoiceResponse.newBuilder()
                .setInvoice(invoiceResponse)
                .build();
        // When
        when(invoiceGrpcAPI.payment(invoiceRequest)).thenReturn(invoiceResponseWrapper);
        var paymentDetails = paymentAPI.executePayment(order);

        // Then

        assertNotNull(paymentDetails);
        assertNotNull(paymentDetails.getId());
        verify(invoiceGrpcAPI, times(1)).payment(any(InvoiceRequest.class));
    }

    @Test
    void executePaymentShouldThrowException() {

        // Given
        var order = OrderMother.order.build();
        Invoice invoice = Invoice.newBuilder()
                .setClientId(order.getCustomerId().toString())
                .setOrderId(order.getOrderId().toString())
                .setValue(order.getTotalPrice().doubleValue())
                .build();
        InvoiceRequest invoiceRequest = InvoiceRequest.newBuilder()
                .setInvoice(invoice)
                .build();
        var paymentUnavailabeException = new UnavailablePaymentException();
        // When
        when(invoiceGrpcAPI.payment(invoiceRequest)).thenThrow(StatusRuntimeException.class);
        UnavailablePaymentException unavailablePaymentApp = assertThrows(UnavailablePaymentException.class, () ->
                paymentAPI.executePayment(order)
        );

        // Then
    }
}