package com.perficient.orderapp.infrastructure.adapter.out.productapp.config;

import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Client {

    @Bean
    FetchProductsGrpc.FetchProductsBlockingStub productsGrpcApi() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        return FetchProductsGrpc.newBlockingStub(channel);
    }
}
