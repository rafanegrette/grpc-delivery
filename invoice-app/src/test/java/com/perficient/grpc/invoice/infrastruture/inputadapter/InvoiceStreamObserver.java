package com.perficient.grpc.invoice.infrastruture.inputadapter;

import com.perficient.proto.invoice.InvoiceResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class InvoiceStreamObserver implements StreamObserver<InvoiceResponse> {
  private final CountDownLatch countDownLatch;
  private InvoiceResponse invoiceResponse;

  public InvoiceStreamObserver(CountDownLatch countDownLatch) {
    this.countDownLatch = countDownLatch;
  }

  @Override
  public void onNext(InvoiceResponse value) {
    this.invoiceResponse = value;
  }

  @Override
  public void onError(Throwable t) {
    this.countDownLatch.countDown();

  }

  @Override
  public void onCompleted() {
    this.countDownLatch.countDown();
  }

  public InvoiceResponse getInvoiceResponse(){
    return invoiceResponse;
  }
}
