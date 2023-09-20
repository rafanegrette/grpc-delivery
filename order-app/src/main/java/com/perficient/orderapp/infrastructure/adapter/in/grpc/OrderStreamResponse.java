package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.order.models.ProductResponse;
import io.grpc.stub.StreamObserver;

import java.util.*;

class OrderStreamResponse implements StreamObserver<ProductRequest> {

    private final StreamObserver<OrderResponse> responseObserver;
    private final Map<Integer, ProductResponse> products = new HashMap();

    public OrderStreamResponse(StreamObserver<OrderResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }
    @Override
    public void onNext(ProductRequest productRequest) {
        var quantity = 0;
        if (products.containsKey(productRequest))
            quantity = products.get(productRequest.getProductId()).getQuantity()
                    + productRequest.getQuantity();
        else {
            quantity = productRequest.getQuantity();
        }

        var productResponse = ProductResponse.newBuilder()
                .setName("generic-product")
                .setQuantity(quantity)
                .setProductId(productRequest.getProductId())
                .build();
        products.put(productResponse.getProductId(), productResponse);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        var orderResponse = OrderResponse.newBuilder()
                .setCustomer("userTest")
                .setOrderId(43)
                .addAllProducts(products.values())
                .build();
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }
}
