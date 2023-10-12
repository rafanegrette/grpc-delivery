package com.perficient.orderapp.infrastructure.adapter.in.grpc.mother;

import com.perficient.orderapp.domain.Cart;
import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartResponse;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.ProductResponse;

import java.util.Set;

public class CartResponseMother {

    static ProductItem product1 = ProductItemMother.product1.build();
    static ProductItem product2 = ProductItemMother.product2.build();
    static Cart cart = CartMother.cart.build();
    public static CartResponse.Builder cartResponse = CartResponse.newBuilder()
            .setCartId(cart.getId().toString())
            .addAllProducts(Set.of(ProductResponse.newBuilder()
                    .setId(product1.getId().toString())
                    .setName(product1.getName())
                    .setQuantity(cart.getProducts().get(product1))
                    .setPrice(product1.getPrice().doubleValue())
                    .build(),
                    ProductResponse.newBuilder()
                            .setId(product2.getId().toString())
                            .setName(product2.getName())
                            .setQuantity(cart.getProducts().get(product2))
                            .setPrice(product2.getPrice().doubleValue())
                            .build()))
            .setTotalPrice(cart.getTotalPrice().doubleValue());
}
