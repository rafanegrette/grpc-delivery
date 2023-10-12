package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.application.PayOrderUseCase;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.OrderMapper;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import java.util.UUID;

@GRpcService
@RequiredArgsConstructor
public class PayCartGrpc extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final PayOrderUseCase payOrderUseCase;

    @Override
    public void payOrder(PaymentRequest request, StreamObserver<OrderResponse> responseObserver) {

        var order = payOrderUseCase.pay(UUID.fromString((request.getCustomerId())));
        var orderResponse = OrderMapper.INSTANCE.map(order);
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }
}
