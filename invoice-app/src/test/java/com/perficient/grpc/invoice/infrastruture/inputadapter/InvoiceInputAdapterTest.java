package com.perficient.grpc.invoice.infrastruture.inputadapter;

import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.proto.invoice.Invoice;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lognet.springboot.grpc.autoconfigure.GRpcServerProperties;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceInputAdapterTest {
  InvoiceInputAdapter  invoiceInputAdapter;

  @Mock
  InvoiceUseCase invoiceUseCase;

  protected ManagedChannel inProcChannel;
  protected Channel seletedChannel;

  @Autowired
  protected GRpcServerProperties gRpcServerProperties;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    invoiceInputAdapter = new InvoiceInputAdapter(invoiceUseCase);
    if (StringUtils.hasText(gRpcServerProperties.getInProcessServerName())) {
      inProcChannel = InProcessChannelBuilder
          .forName(gRpcServerProperties.getInProcessServerName())
          .usePlaintext()
          .build();
    } else {
      assertTrue(false, "No in-processChannel Available");
    }
  }

  @Test
  void invoice() {
    //Given
    InvoiceServiceGrpc.InvoiceServiceStub invoiceService = InvoiceServiceGrpc.newStub(seletedChannel);
    InvoiceRequest request = InvoiceRequest.newBuilder()
            .setInvoice(Invoice.newBuilder()
                .setClient("TestClient")
                .setOrderId("OrderTest")
                .setValue(112233)
                .build())
                .build();
    CountDownLatch latch = new CountDownLatch(1);
    InvoiceStreamObserver invoiceStreamObserver = new InvoiceStreamObserver(latch);
    invoiceService.invoice(request, invoiceStreamObserver);
    //invoiceInputAdapter.invoice(request, );
  }
}