package com.perficient.orderapp.infrastructure.adapter.out.productapp;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.port.RetrieveProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.mapper.ProductMapper;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc.FetchProductsBlockingStub;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.ProductRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductApi implements RetrieveProductItem {

    private final FetchProductsBlockingStub productsGrpcApi;

    public ProductApi(FetchProductsBlockingStub productsGrpcApi) {
        this.productsGrpcApi = productsGrpcApi;
    }

    public ProductApi() {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        productsGrpcApi = FetchProductsGrpc.newBlockingStub(channel);
    }
    @Override
    public ProductItem retrieve(UUID productId) {
        var productRequest = ProductRequest.newBuilder()
                .setProductId(productId.toString())
                .build();
        var menuResponse = productsGrpcApi.getProduct(productRequest);
        return ProductMapper.INSTANCE.map(menuResponse);
    }
}
