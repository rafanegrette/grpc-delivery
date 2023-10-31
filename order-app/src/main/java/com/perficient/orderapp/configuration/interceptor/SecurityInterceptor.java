package com.perficient.orderapp.configuration.interceptor;

import io.grpc.ForwardingServerCallListener.SimpleForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
@GRpcGlobalInterceptor
public class SecurityInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        try {
            var user = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var email = user.getClaims().get("email");
            log.info("User authenticated {}", email.toString());
        } catch (Exception e) {
            log.warn("No authenticated mode");
        }        return new SimpleForwardingServerCallListener<ReqT>(next.startCall(call, headers)) {
            @Override
            public void onMessage(ReqT request) {
                super.onMessage(request);
            }
        };
    }
}
