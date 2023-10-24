package com.perficient.orderapp.infrastructure.adapter.out.productapp.config;

import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductClient {

    @Value("${apps.product-app.port}")
    private String productAppPort;

    @Value("${apps.product-app.host}")
    private String productAppHost;

    @Bean
    FetchProductsGrpc.FetchProductsBlockingStub productsGrpcApi() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress(productAppHost, Integer.parseInt(productAppPort))
                .usePlaintext()
                .build();
        return FetchProductsGrpc.newBlockingStub(channel);
    }
}
