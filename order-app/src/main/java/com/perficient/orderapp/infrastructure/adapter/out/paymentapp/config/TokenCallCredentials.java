package com.perficient.orderapp.infrastructure.adapter.out.paymentapp.config;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class TokenCallCredentials extends CallCredentials {

  @Value("${apps.payment-app.credential}")
  private String credentials;

  @Override
  public void applyRequestMetadata(RequestInfo requestInfo, Executor appExecutor, MetadataApplier applier) {
    appExecutor.execute(() -> {
      try {
        Metadata headers = new Metadata();
        Metadata.Key<String> authKey = Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);
        headers.put(authKey, "Bearer " + credentials);
        applier.apply(headers);

      } catch (Throwable e) {
        applier.fail(Status.UNAUTHENTICATED.withCause(e));
      }
    });
  }
}
