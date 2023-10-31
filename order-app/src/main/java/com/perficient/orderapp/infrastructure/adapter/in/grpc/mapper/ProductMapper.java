package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse map(ProductItem productItem);

    default List<ProductResponse> map(Map<ProductItem, Integer> productItem) {
        List<ProductResponse> productResponses = new ArrayList<>();
        productItem.forEach((product, quantity) -> {
            var productResponse = ProductResponse.newBuilder(map(product))
                    .setQuantity(quantity)
                    .build();
            productResponses.add(productResponse);
        });
        return productResponses;
    }
}
