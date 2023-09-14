package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.GenerateOrdersGrpc;
import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GenerateOrder extends GenerateOrdersGrpc.GenerateOrdersImplBase {

    @Override
    public StreamObserver<ProductRequest> create(StreamObserver<OrderResponse> responseObserver) {
        return new OrderStreamResponse(responseObserver);
    }
}
