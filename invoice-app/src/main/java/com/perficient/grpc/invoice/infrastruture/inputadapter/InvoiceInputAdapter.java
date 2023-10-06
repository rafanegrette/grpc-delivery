package com.perficient.grpc.invoice.infrastruture.inputadapter;

import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class InvoiceInputAdapter extends InvoiceServiceGrpc.InvoiceServiceImplBase {
  private final InvoiceUseCase invoiceUseCase;
  public InvoiceInputAdapter(InvoiceUseCase invoiceUseCase) {
    this.invoiceUseCase = invoiceUseCase;
  }

  @Override
  public void invoice(InvoiceRequest request, StreamObserver<InvoiceResponse> responseObserver) {
    Invoice invoice = this.invoiceUseCase.createInvoice(request);
    InvoiceResponse invoiceResponse = InvoiceResponse.newBuilder()
        .setInvoice(com.perficient.proto.invoice.Invoice.newBuilder()
            .setInvoiceId(invoice.getId().toString())
            .setClient(invoice.getCustomerId())
            .setOrderId(invoice.getOrderId())
            .setValue(invoice.getValue())
            .build())
        .setResult(invoice.getResult())
        .build();
    responseObserver.onNext(invoiceResponse);
    responseObserver.onCompleted();
  }
}
