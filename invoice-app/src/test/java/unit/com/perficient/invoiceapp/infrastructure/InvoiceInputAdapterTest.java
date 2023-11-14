package com.perficient.invoiceapp.infrastructure;

import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.domain.Type;
import com.perficient.grpc.invoice.infrastructure.inputadapter.InvoiceInputAdapter;
import com.perficient.grpc.invoice.infrastructure.mapper.InvoiceMapper;
import com.perficient.proto.invoice.InvoiceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InvoiceInputAdapterTest {

  @InjectMocks
  InvoiceInputAdapter  invoiceInputAdapter;

  @Mock
  private InvoiceUseCase invoiceUseCase;

  @Mock
  private InvoiceMapper invoiceMapper;

  //@Test
  void getPay() {
    Invoice invoice = Invoice.builder()
        .value(12233)
        .typePay(Type.CREDIT)
        .clientId("mrs")
        .paymentDate(new Date())
        .orderId("OID")
        .build();


   // invoiceInputAdapter.payment();

  }


}