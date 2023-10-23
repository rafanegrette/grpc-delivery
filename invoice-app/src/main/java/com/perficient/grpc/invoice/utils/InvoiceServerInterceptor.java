package com.perficient.grpc.invoice.utils;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@GRpcGlobalInterceptor
public class InvoiceServerInterceptor implements io.grpc.ServerInterceptor{
  private static final Logger logger =Logger.getLogger(InvoiceServerInterceptor.class.getName());
  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    logger.info("==== [Server Interceptor] : Remote Method Invoked - " + call.getMethodDescriptor().getFullMethodName());
    ServerCall<ReqT, RespT> serverCall = new InvoiceServerCall<>(call);
    return new InvoiceServerCallListener<>(next.startCall(serverCall, headers));
  }
}
