package com.perficient.grpc.invoice.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class InvoiceClient {
  public static void main(String[] args) {
    System.out.println("Invoice client grpc");

    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50052)
        .usePlaintext()
        .build();

    //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(managedChannel);
    /*
    InvoiceServiceGrpc.InvoiceServiceBlockingStub syncClient = InvoiceServiceGrpc.newBlockingStub(managedChannel);
    InvoiceRequest request = InvoiceRequest.newBuilder()
        .setClient("112233")
        .setProductId(2233)
        .setValue(19300)
        .build();

    InvoiceManyTimesRequest invoiceManyTimesRequest = InvoiceManyTimesRequest.newBuilder()
        .setClient("22")
        .setProductId(11)
        .setValue(11)
        .build();

     */
    /*
    InvoiceResponse response = syncClient.invoice(request);
    System.out.println("Invoice response:::"+response.getInvoice().getInvoiceId());
    System.out.println(("Invoice response:::"+response.getInvoice().getCreationDate().getSeconds()));
     */

    /*
    syncClient.invoiceManyTimesServer(invoiceManyTimesRequest).forEachRemaining(invoiceManyTimesResponse -> {
      System.out.println(invoiceManyTimesResponse.getInvoice().getInvoiceId());
      System.out.println(invoiceManyTimesResponse.getInvoice().getCreationDate().getSeconds());

     */
    //});


    System.out.println("Shutting down channel");
    managedChannel.shutdown();
  }
}
