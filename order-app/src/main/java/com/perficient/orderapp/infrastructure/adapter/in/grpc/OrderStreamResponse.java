package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.order.models.OrderResponse;
import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.application.port.in.AddProductUseCase;
import com.perficient.orderapp.application.port.in.RetrieveOrderUseCase;
import com.perficient.orderapp.domain.model.Customer;
import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.OrderMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
class OrderStreamResponse implements StreamObserver<ProductRequest> {

    private final UUID CUSTOMER_ID = UUID.fromString("6d303f86-3e90-491e-b98c-96b7d32b0e9d");
    private final StreamObserver<OrderResponse> responseObserver;

    private final AddProductUseCase addProductUseCase;
    private final RetrieveOrderUseCase retrieveOrderUseCase;

    @Override
    public void onNext(ProductRequest productRequest) {
        var order = Order.builder()
                .orderId(UUID.fromString(productRequest.getOrderId()))
                .customerId(CUSTOMER_ID)
                .build();

        addProductUseCase.addProductToOrder(order,
                UUID.fromString(productRequest.getId()),
                productRequest.getQuantity());
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onCompleted() {
        var orderResponse = retrieveOrderUseCase.retrieveCurrentOrder(new Customer(CUSTOMER_ID, "guy1"));
        responseObserver.onNext(OrderMapper.INSTANCE.map(orderResponse));
        responseObserver.onCompleted();
    }
}
