package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.CartMapper;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.AddProductRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartResponse;
import com.perficient.orderapp.application.AddProductUseCase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class CartStreamResponse implements StreamObserver<AddProductRequest> {

    private final UUID CUSTOMER_ID = UUID.fromString("63d89df6-32bb-47ea-861c-a72605a833fe");
    private final StreamObserver<CartResponse> responseObserver;

    private final AddProductUseCase addProductUseCase;

    @Override
    public void onNext(AddProductRequest productRequest) {
        addProductUseCase.addProductToCart(
                UUID.fromString(productRequest.getCustomerId()),
                UUID.fromString(productRequest.getId()),
                productRequest.getQuantity());
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        var cart = addProductUseCase.retrieveCart(CUSTOMER_ID);
        responseObserver.onNext(CartMapper.INSTANCE.map(cart));
        responseObserver.onCompleted();
    }
}
