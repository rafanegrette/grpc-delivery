package com.perficient.grpc.invoice.infrastruture.inputadapter;

import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceResponseMapper;
import com.perficient.proto.invoice.InvoiceRequest;
import com.perficient.proto.invoice.InvoiceResponse;
import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.logging.Logger;

@GRpcService
public class InvoiceInputAdapter extends InvoiceServiceGrpc.InvoiceServiceImplBase {
  private static final Logger logger = Logger.getLogger(InvoiceInputAdapter.class.getName());
  private final InvoiceUseCase invoiceUseCase;

  public InvoiceInputAdapter(InvoiceUseCase invoiceUseCase) {
    this.invoiceUseCase = invoiceUseCase;
  }

  @Override
  public void payment(InvoiceRequest request, StreamObserver<InvoiceResponse> responseObserver) {

    Invoice invoice = this.invoiceUseCase.createInvoice(request);
    if(!invoice.getId().equals("-1")) {
      InvoiceResponse response = InvoiceResponseMapper.INSTANCE.toInvoiceResponse(invoice);
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    else {
      responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid payment value.").asException());
    }
  }
}
