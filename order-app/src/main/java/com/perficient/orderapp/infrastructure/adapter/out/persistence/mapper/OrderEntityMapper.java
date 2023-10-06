package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.OrderEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.ProductItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderEntityMapper {

    OrderEntityMapper INSTANCE = Mappers.getMapper(OrderEntityMapper.class);

    @Mapping(source = "productItems", target = "productItemEntities")
    @Mapping(source = "paymentDetails", target = "paymentDetailsEntity")
    OrderEntity map(Order order);

    ProductItemEntity map(ProductItem productItem);
}
