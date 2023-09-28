package com.perficient.orderapp.infrastructure.adapter.out.productapp.mapper;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.MenuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    @Mapping(target = "discount", constant = "0")
    @Mapping(source = "productId", target = "id")
    ProductItem map(MenuResponse menuResponse);

}

