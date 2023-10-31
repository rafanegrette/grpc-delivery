package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartEntityMapper {

    CartEntityMapper INSTANCE = Mappers.getMapper(CartEntityMapper.class);

    @Mapping(source = "products", target = "productItemEntities")
    @Mapping(source = "id", target = "cartId")
    CartEntity map(Cart cart);

    @Mapping(source = "productItemEntities", target = "products")
    @Mapping(source = "cartId", target = "id")
    Cart map(CartEntity cartEntity);
}
