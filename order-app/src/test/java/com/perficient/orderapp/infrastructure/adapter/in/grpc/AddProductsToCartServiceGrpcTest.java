package com.perficient.orderapp.infrastructure.adapter.in.grpc;

import com.perficient.orderapp.domain.*;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.AddProductRequest;
import com.perficient.orderapp.infrastructure.adapter.in.grpc.model.CartResponse;
import com.perficient.orderapp.application.AddProductUseCase;
import io.grpc.internal.testing.StreamRecorder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddProductsToCartServiceGrpcTest {

    @Mock
    AddProductUseCase addProductUseCase;

    @InjectMocks
    AddProductsToCartServiceGrpc addProductServiceGrpc;

    @Test
    void AddProductsToOrder() {
        // GIVEN
        var customer = CustomerMother.customer.build();
        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();
        var productRequest1 = AddProductRequest.newBuilder()
                .setId(product1.getId().toString())
                .setCustomerId(CustomerMother.customerId.toString())
                .setQuantity(1)
                .build();
        var productRequest2 = AddProductRequest.newBuilder()
                .setId(product2.getId().toString())
                .setCustomerId(CustomerMother.customerId.toString())
                .setQuantity(2)
                .build();

        var cartGiven = getCurrentCart(Map.of(product1, 10,
                product2, 10));
        given(addProductUseCase.retrieveCart(any(UUID.class))).willReturn(cartGiven);

        // WHEN

        StreamRecorder<CartResponse> cartResponseObserver = StreamRecorder.create();
        var productsObserver = addProductServiceGrpc.addProduct(cartResponseObserver);

        for (int i = 0; i < 10; i++) {
            productsObserver.onNext(productRequest1);
            productsObserver.onNext(productRequest2);
        }
        productsObserver.onCompleted();

        // THEN

        var orderResponses = cartResponseObserver.getValues();

        assertEquals(1, orderResponses.size());

        verify(addProductUseCase, times(10))
                .addProductToCart(eq(customer.getId()), eq(product1.getId()), eq(1));
        verify(addProductUseCase, times(10))
                .addProductToCart(eq(customer.getId()), eq(product2.getId()), eq(2));

    }

    @Test
    void addProductToCartReviewProducts() {
        // GIVEN

        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();

        var productRequest1 = AddProductRequest.newBuilder()
                .setId(product1.getId().toString())
                .setCustomerId(CustomerMother.customerId.toString())
                .setQuantity(1)
                .build();
        var productRequest2 = AddProductRequest.newBuilder()
                .setId(product2.getId().toString())
                .setCustomerId(CustomerMother.customerId.toString())
                .setQuantity(2)
                .build();

        var cartGiven = getCurrentCart(Map.of(product1, 1,
                                product2, 1));

        // WHEN
        when(addProductUseCase.retrieveCart(any(UUID.class))).thenReturn(cartGiven);

        StreamRecorder<CartResponse> cartResponseObserver = StreamRecorder.create();
        var productsObserver = addProductServiceGrpc.addProduct(cartResponseObserver);

        for (int i = 0; i < 10; i++) {
            productsObserver.onNext(productRequest1);
            productsObserver.onNext(productRequest2);
        }
        productsObserver.onCompleted();

        // THEN

        var cartResponses = cartResponseObserver.getValues();

        assertEquals(1, cartResponses.size());
        assertEquals(2, cartResponses.get(0).getProductsList().size());
    }

    private Cart getCurrentCart(Map<ProductItem, Integer> productItems) {
        return new Cart(UUID.randomUUID(),
                productItems,
                ProductItemMother.total_price);
    }

}