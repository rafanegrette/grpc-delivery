package com.perficient.orderapp.ingrastructure.adapter.in.grpc;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class CartStreamingObserver implements StreamObserver<CartResponse> {
    private final CountDownLatch latch;
    private CartResponse cartResponse;
    public CartStreamingObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(CartResponse cartResponse) {
        this.cartResponse = cartResponse;
    }

    @Override
    public void onError(Throwable t) {
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        latch.countDown();
    }

    public CartResponse getCart() {
        return cartResponse;
    }
}
