package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    default CartResponse map(Cart cart) {
        return CartResponse.newBuilder()
                .setCartId(cart.getId().toString())
                .addAllProducts(ProductMapper.INSTANCE.map(cart.getProducts()))
                .setTotalPrice(cart.getTotalPrice().doubleValue())
                .build();
    }

}
