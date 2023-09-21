package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.ProductsOrderServiceGrpc.ProductsOrderServiceImplBase;
import com.perficient.order.models.ProductRequest;
import com.perficient.order.models.OrderResponse;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.in.RetrieveOrderUseCase;
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
