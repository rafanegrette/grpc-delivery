package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.GenerateOrdersGrpc;
import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.in.CreateOrderUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@RequiredArgsConstructor
@GRpcService
public class GenerateOrder extends GenerateOrdersGrpc.GenerateOrdersImplBase {

    private final AddProductUseCase addProductUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    @Override
    public StreamObserver<ProductRequest> create(StreamObserver<OrderResponse> responseObserver) {
        return new OrderStreamResponse(responseObserver, addProductUseCase, createOrderUseCase);
    }
}
