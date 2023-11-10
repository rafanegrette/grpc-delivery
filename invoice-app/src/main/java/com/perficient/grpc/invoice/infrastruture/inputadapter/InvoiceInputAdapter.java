package com.perficient.grpc.invoice.infrastruture.inputadapter;

import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceMapper;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceResponseMapper;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@GRpcService
public class InvoiceInputAdapter extends InvoiceServiceGrpc.InvoiceServiceImplBase {
  private final InvoiceUseCase invoiceUseCase;
  private final InvoiceMapper invoiceMapper;

  public InvoiceInputAdapter(InvoiceUseCase invoiceUseCase, InvoiceMapper invoiceMapper) {
    this.invoiceUseCase = invoiceUseCase;
    this.invoiceMapper = invoiceMapper;
  }

  @Override
  public void payment(InvoiceRequest request, StreamObserver<InvoiceResponse> responseObserver) {
    Invoice invoice = this.invoiceUseCase.createInvoice(this.invoiceMapper.toEntity(request));
    if (!invoice.getId().equals("-1")) {
      InvoiceResponse response = InvoiceResponseMapper.INSTANCE.toInvoiceResponse(invoice);
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } else {
      responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid payment value.").asException());
    }
  }
}
