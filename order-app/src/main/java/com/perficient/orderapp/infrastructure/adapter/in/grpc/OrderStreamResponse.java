package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.order.models.ProductResponse;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.in.CreateOrderUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Product;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.ProductMapper;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.OrderMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
class OrderStreamResponse implements StreamObserver<ProductRequest> {

    private final StreamObserver<OrderResponse> responseObserver;

    private final AddProductUseCase addProductUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    @Override
    public void onNext(ProductRequest productRequest) {

        addProductUseCase.addProduct(new Customer("guy1"), ProductMapper
                .INSTANCE
                .map(productRequest));
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        var orderResponse = createOrderUseCase.create(new Customer("guy1"));
        responseObserver.onNext(OrderMapper.INSTANCE.map(orderResponse));
        responseObserver.onCompleted();
    }
}
