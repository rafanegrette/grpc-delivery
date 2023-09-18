package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.order.models.ProductResponse;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Product;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
class OrderStreamResponse implements StreamObserver<ProductRequest> {

    private final StreamObserver<OrderResponse> responseObserver;
    //private final Map<Integer, ProductResponse> products = new HashMap<>();

    private final AddProductUseCase addProductUseCase;

    @Override
    public void onNext(ProductRequest productRequest) {
        //var quantity = 0;
        /*if (products.containsKey(productRequest))
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
        products.put(productResponse.getProductId(), productResponse);*/

        addProductUseCase.addProduct(new Customer(), new Product());
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        var orderResponse = OrderResponse.newBuilder()
                .setCustomer("userTest")
                .setOrderId(43)
                //.addAllProducts(products.values())
                .addProducts(ProductResponse.newBuilder().build())
                .build();
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }
}
