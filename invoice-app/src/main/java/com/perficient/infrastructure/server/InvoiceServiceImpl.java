package com.perficient.infrastructure.server;

import com.google.protobuf.Timestamp;
import com.perficient.proto.invoice.*;
import io.grpc.stub.StreamObserver;


public class InvoiceServiceImpl extends InvoiceServiceGrpc.InvoiceServiceImplBase {

  @Override
  public void invoice(InvoiceRequest request, StreamObserver<InvoiceResponse> responseObserver) {
    //Extract the fields we need
    String client = request.getClient();
    double value = request.getValue();
    int product = request.getProductId();

    //Create the response
    Invoice invoice = Invoice.newBuilder()
        .setInvoiceId(112233)
        .setCreationDate(Timestamp.newBuilder().setSeconds(111).build())
        .setResult(true)
        .build();
    InvoiceResponse resultInvoice = InvoiceResponse.newBuilder()
        .setInvoice(invoice)
        .build();

    //Send the response
    responseObserver.onNext(resultInvoice);

    //complete the RPC call
    responseObserver.onCompleted();
  }

  @Override
  public void invoiceManyTimesServer(InvoiceManyTimesRequest request, StreamObserver<InvoiceManyTimesResponse> responseObserver) {
    String client = request.getClient();
    double value = request.getValue();
    int productId = request.getProductId();

    try {
      for(int i = 0; i < 10; i++){
        InvoiceManyTimesResponse invoiceManyTimesResponse = InvoiceManyTimesResponse
            .newBuilder()
            .setInvoice(Invoice.newBuilder().setInvoiceId(i)
                .setCreationDate(Timestamp.newBuilder().setSeconds(i).build()))
            .build();

        responseObserver.onNext(invoiceManyTimesResponse);
        Thread.sleep(1000L);
      }
    }catch (InterruptedException e){
      e.printStackTrace();
    }finally {
      responseObserver.onCompleted();
    }
  }
}
