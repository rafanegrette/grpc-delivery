package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.application.CreateOrderUseCase;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CustomerRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderServiceGrpc.OrderServiceImplBase;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.OrderMapper;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

import java.util.UUID;

@GRpcService
public class CreateOrderServiceGrpc extends OrderServiceImplBase {

    CreateOrderUseCase createOrderUseCase;

    @Override
    public void create(CustomerRequest request, StreamObserver<OrderResponse> responseObserver) {
        var order = createOrderUseCase.create(UUID.fromString(request.getId()));
        var orderResponse = OrderMapper.INSTANCE.map(order);
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }
}
