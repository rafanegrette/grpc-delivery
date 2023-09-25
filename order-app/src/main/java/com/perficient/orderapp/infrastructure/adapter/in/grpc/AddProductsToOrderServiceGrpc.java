package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.ProductsOrderServiceGrpc.ProductsOrderServiceImplBase;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.ProductRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.application.AddProductUseCase;
import com.perficient.orderapp.application.RetrieveOrderUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@RequiredArgsConstructor
@GRpcService
public class AddProductsToOrderServiceGrpc extends ProductsOrderServiceImplBase {

    private final AddProductUseCase addProductUseCase;
    private final RetrieveOrderUseCase retrieveOrderUseCase;

    @Override
    public StreamObserver<ProductRequest> addProductToOrder(StreamObserver<OrderResponse> responseObserver) {
        return new OrderStreamResponse(responseObserver, addProductUseCase, retrieveOrderUseCase);
    }

}
