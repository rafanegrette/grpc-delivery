package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.AddProductRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartServiceGrpc.CartServiceImplBase;
import com.perficient.orderapp.application.AddProductUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.security.access.annotation.Secured;

@Slf4j
@Secured({})
@RequiredArgsConstructor
@GRpcService
public class AddProductsToCartServiceGrpc extends CartServiceImplBase {

    private final AddProductUseCase addProductUseCase;

    @Override
    public StreamObserver<AddProductRequest> addProduct(StreamObserver<CartResponse> responseObserver) {
        return new CartStreamResponse(responseObserver, addProductUseCase);
    }

}
