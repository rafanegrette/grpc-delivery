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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
@Secured({})
@RequiredArgsConstructor
@GRpcService
public class AddProductsToCartServiceGrpc extends CartServiceImplBase {

    private final AddProductUseCase addProductUseCase;

    @Override
    public StreamObserver<AddProductRequest> addProduct(StreamObserver<CartResponse> responseObserver) {
        try {
            var user = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var email = user.getClaims().get("email");
            log.info(email.toString());
        } catch (Exception e) {
            log.warn("No authenticated mode");
        }
        return new CartStreamResponse(responseObserver, addProductUseCase);
    }

}
