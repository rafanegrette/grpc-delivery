package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "name", ignore = true)
    Product map (ProductRequest productRequest);
}
