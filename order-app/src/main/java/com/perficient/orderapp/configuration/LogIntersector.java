package com.perficient.orderapp.configuration;

import io.grpc.ServerInterceptor;
import io.grpc.ServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCallHandler;
import io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;

@Slf4j
@GRpcGlobalInterceptor
public class LogIntersector implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        log.info(call.getMethodDescriptor().getFullMethodName());
        return new SimpleForwardingServerCallListener<ReqT>(next.startCall(call, headers)) {
            @Override
            public void onMessage(ReqT request) {
                log.info(request.toString());
                super.onMessage(request);
            }
        };
    }
}
