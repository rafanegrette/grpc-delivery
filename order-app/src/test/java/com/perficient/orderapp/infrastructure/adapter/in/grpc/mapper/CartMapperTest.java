package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.orderapp.domain.mother.CartMother;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.mother.CartResponseMother;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartMapperTest {

    @Test
    void test_domaint_to_grpc() {
        // GIVEN

        var cart = CartMother.cart.build();
        var cartResponse = CartResponseMother.cartResponse.build();

        // WHEN

        var cartResponseReturned = CartMapper.INSTANCE.map(cart);

        // THEN
        assertEquals(cartResponse.getCartId(), cartResponseReturned.getCartId());
        assertEquals(cartResponse.getTotalPrice(), cartResponseReturned.getTotalPrice());
        assertTrue(cartResponse.getProductsList().containsAll(cartResponseReturned.getProductsList()));
    }

}
