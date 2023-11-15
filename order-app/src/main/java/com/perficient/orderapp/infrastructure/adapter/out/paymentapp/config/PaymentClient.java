package com.perficient.orderapp.infrastructure.adapter.out.paymentapp.config;

import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class PaymentClient {

  @Value("${apps.payment-app.port}")
  private String paymentAppPort;
  @Value("${apps.payment-app.host}")
  private String paymentAppHost;
  @Value("${apps.payment-app.duration}")
  private long paymentAppDuration;
  private final TokenCallCredentials tokenCallCredentials;

  ManagedChannel channel = null;

  public PaymentClient(TokenCallCredentials tokenCallCredentials) {
    this.tokenCallCredentials = tokenCallCredentials;
  }

  @Bean
  public InvoiceServiceGrpc.InvoiceServiceBlockingStub invoiceServiceGrpcApi() {
    channel = ManagedChannelBuilder.forAddress(paymentAppHost,
            Integer.parseInt(paymentAppPort))
        .usePlaintext()
        .build();
    log.info("paymentAppDuration:::" + paymentAppDuration);

    return InvoiceServiceGrpc.newBlockingStub(channel).withDeadlineAfter(paymentAppDuration,
        TimeUnit.MILLISECONDS).withCallCredentials(tokenCallCredentials);
  }

  public void closeChannel() throws InterruptedException {
    log.info("Closing gRPC channel.");
    // Close the gRPC channel properly.
    if (channel != null && !channel.isShutdown()) {
      channel.shutdown();
      channel.awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
