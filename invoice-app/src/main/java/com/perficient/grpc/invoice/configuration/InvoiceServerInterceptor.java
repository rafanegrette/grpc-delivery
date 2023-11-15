package com.perficient.grpc.invoice.configuration;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@GRpcGlobalInterceptor
public class InvoiceServerInterceptor implements io.grpc.ServerInterceptor {
  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    log.info("==== [Server Interceptor] : Remote Method Invoked - " + call.getMethodDescriptor().getFullMethodName());
    ServerCall<ReqT, RespT> serverCall = new InvoiceServerCall<>(call);
    return new InvoiceServerCallListener<>(next.startCall(serverCall, headers));
  }
}
