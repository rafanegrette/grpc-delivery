package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.google.protobuf.Timestamp;
import com.perficient.orderapp.domain.PaymentDetails;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.OrderResponse;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.PaymentDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    default OrderResponse map(Order order) {
        return OrderResponse.newBuilder()
                .setOrderId(order.getOrderId().toString())
                .setCustomerId(order.getCustomerId().toString())
                .setOrderStatus(order.getOrderStatus().toString())
                .setPaymentDetail(map(order.getPaymentDetails()))
                .setCreationDate(map(order.getCreationDate()))
                .addAllProducts(ProductMapper.INSTANCE.map(order.getProductItems()))
                .build();
    }


    @Mapping(source = "paymentDate", target = "date")
    PaymentDetail map(PaymentDetails paymentDetails);

    default Timestamp map(LocalDateTime localDateTime) {
        var instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
