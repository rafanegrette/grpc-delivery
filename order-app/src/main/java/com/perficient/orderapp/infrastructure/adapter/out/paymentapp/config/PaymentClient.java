package com.perficient.orderapp.infrastructure.adapter.out.paymentapp.config;

import com.perficient.proto.invoice.InvoiceServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PaymentClient {

    @Value("${apps.payment-app.port}")
    private String paymentAppPort;
    @Value("${apps.payment-app.host}")
    private String paymentAppHost;

    @Bean
    InvoiceServiceGrpc.InvoiceServiceBlockingStub invoiceServiceGrpcApi() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(paymentAppHost, Integer.parseInt(paymentAppPort))
                .usePlaintext()
                .build();
        return InvoiceServiceGrpc.newBlockingStub(channel);
    }
}
