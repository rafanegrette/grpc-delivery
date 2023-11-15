package com.perficient.grpc.invoice.configuration.security;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

@Component
@Slf4j
@GRpcGlobalInterceptor
@Profile("production")
public class TokenAuthInterceptor implements ServerInterceptor {
  @Value("${apps.payment-app.credential}")
  private String credentials;
  private static final ServerCall.Listener NOOP_LISTENER = new ServerCall.Listener() {
  };
  @Value("${apps.payment-app.user}")
  private String user;
  private static final Context.Key<String> USER_ID_CTX_KEY = Context.key("userId");

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                               Metadata headers,
                                                               ServerCallHandler<ReqT, RespT> next) {
    log.info("==== [Server Interceptor] : Remote Method Invoked Credentials - " + call.getMethodDescriptor().getFullMethodName());
    String tokenString = headers.get(Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER));
    if (tokenString == null) {
      call.close(Status.UNAUTHENTICATED.withDescription("Token value is missing in Metadata"), headers);
      return NOOP_LISTENER;
    }
    if (validUser(tokenString)) {
      Context context = Context.current().withValue(USER_ID_CTX_KEY, user);
      return Contexts.interceptCall(context, call, headers, next);
    } else {
      log.info("Verification failed - Unauthenticated!");
      call.close(Status.UNAUTHENTICATED.withDescription("Invalid user token"), headers);
      return NOOP_LISTENER;
    }
  }

  private boolean validUser(String basicAuthString) {
    String token = basicAuthString.substring("Bearer ".length()).trim();
    return credentials.equals(token);
  }
}
