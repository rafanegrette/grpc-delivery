package com.perficient.orderapp.infrastructure.adapter.out.paymentapp;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.domain.excepton.UnavailablePaymentException;
import com.perficient.orderapp.domain.port.PaymentApp;
import com.perficient.orderapp.infrastructure.adapter.out.paymentapp.config.PaymentClient;
import com.perficient.proto.invoice.Invoice;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentAPI implements PaymentApp {
  private final PaymentClient paymentClient;

  @Override
  public PaymentDetails executePayment(Order order) throws InterruptedException {
    InvoiceRequest invoiceRequest = InvoiceRequest.newBuilder()
        .setInvoice(Invoice.newBuilder()
            .setValue(order.getTotalPrice().doubleValue())
            .setClientId(order.getCustomerId().toString())
            .setOrderId(order.getOrderId().toString())
            .build())
        .build();
    InvoiceServiceGrpc.InvoiceServiceBlockingStub stub = paymentClient.invoiceServiceGrpcApi();
    InvoiceResponse invoiceResponse = null;
    try {
      invoiceResponse = stub.payment(invoiceRequest);
    } catch (StatusRuntimeException ex) {
      if (ex.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
        log.info("Deadline Exceeded -> " + ex.getMessage());
        throw new UnavailablePaymentException();
      } else {
        log.info("Unspecified error from the service -> " + ex.getMessage());
        throw new UnavailablePaymentException();
      }
    }
    assert invoiceResponse != null;
    paymentClient.closeChannel();
    return PaymentDetails.builder()
        .paymentDate(LocalDateTime.now())
        .amount(order.getTotalPrice())
        .id(UUID.nameUUIDFromBytes(invoiceResponse.getInvoice().getInvoiceId().getBytes()))
        .build();
  }
}
