package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.application.PayOrderUseCase;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper.OrderMapper;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.security.access.annotation.Secured;

import java.util.UUID;

@Secured({})
@GRpcService
@RequiredArgsConstructor
public class PayCartGrpc extends PaymentServiceGrpc.PaymentServiceImplBase {

  private final PayOrderUseCase payOrderUseCase;

  @Override
  public void payOrder(PaymentRequest request, StreamObserver<OrderResponse> responseObserver) {

    Order order = null;
    try {
      order = payOrderUseCase.pay(UUID.fromString((request.getCustomerId())));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    var orderResponse = OrderMapper.INSTANCE.map(order);
    responseObserver.onNext(orderResponse);
    responseObserver.onCompleted();
  }
}
