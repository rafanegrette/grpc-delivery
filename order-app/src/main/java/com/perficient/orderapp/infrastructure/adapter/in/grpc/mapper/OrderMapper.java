package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    default OrderResponse map(Order order) {
        return OrderResponse.newBuilder()
                .setOrderId(order.getOrderId().toString())
                .setCustomerId(order.getCustomerId().toString())
                .setOrderStatus(order.getOrderStatus().toString())
                .addAllProducts(ProductMapper.INSTANCE.map(order.getProductItems()))
                .build();
    }
}
