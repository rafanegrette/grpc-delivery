package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.order.models.ProductResponse;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Product;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.ProductMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
class OrderStreamResponse implements StreamObserver<ProductRequest> {

    private final StreamObserver<OrderResponse> responseObserver;

    private final AddProductUseCase addProductUseCase;

    @Override
    public void onNext(ProductRequest productRequest) {

        addProductUseCase.addProduct(new Customer(), ProductMapper
                .INSTANCE
                .map(productRequest));
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        var orderResponse = OrderResponse.newBuilder()
                .setCustomer("userTest")
                .setOrderId("dgf")
                //.addAllProducts(products.values())
                .addProducts(ProductResponse.newBuilder().build())
                .build();
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }
}
