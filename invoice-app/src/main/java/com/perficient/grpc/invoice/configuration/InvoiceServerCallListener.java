package com.perficient.grpc.invoice.configuration;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class InvoiceServerCallListener<R> extends ForwardingServerCallListener<R> {

  private final ServerCall.Listener<R> delegate;

  public InvoiceServerCallListener(ServerCall.Listener<R> delegate) {
    this.delegate = delegate;
  }

  @Override
  public ServerCall.Listener<R> delegate() {
    return delegate;
  }

  @Override
  public void onMessage(R message) {
    log.info("Message Received from client -> Service " + message);
    super.onMessage(message);
  }
}
