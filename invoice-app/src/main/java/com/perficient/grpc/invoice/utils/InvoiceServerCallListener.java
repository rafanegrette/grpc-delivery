package com.perficient.grpc.invoice.utils;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;

import java.util.logging.Logger;

public class InvoiceServerCallListener<R> extends ForwardingServerCallListener<R> {
  private final static Logger logger = Logger.getLogger(InvoiceServerCallListener.class.getName());

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
    logger.info("Message Received from client -> Service "+ message);
    super.onMessage(message);
  }
}
