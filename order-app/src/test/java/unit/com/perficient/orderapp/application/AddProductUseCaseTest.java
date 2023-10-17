package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.excepton.ProductNotFoundException;
import com.perficient.orderapp.domain.mother.CustomerMother;
import com.perficient.orderapp.domain.mother.ProductItemMother;
import com.perficient.orderapp.domain.port.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddProductUseCaseTest {

    @Mock
    RetrieveProductItem retrieveProductItem;

    @Mock
    RetrieveCustomer retrieveCustomer;
    @Mock
    SaveCustomerCart saveCustomerCart;
    @InjectMocks
    AddProductUseCase addProductUseCase;

    @Test()
    void add_product_to_cart_should_success() {
        // GIVEN
        var product_id_1 = ProductItemMother.product_id_1;
        var product_id_2 = ProductItemMother.product_id_2;
        var product1 = ProductItemMother.product1.build();
        var product2 = ProductItemMother.product2.build();
        var customer = CustomerMother.customer.build();

        given(retrieveCustomer.retrieve(customer.getId())).willReturn(customer);
        given(retrieveProductItem.retrieve(product_id_1)).willReturn(product1);
        given(retrieveProductItem.retrieve(product_id_2)).willReturn(product2);

        // WHEN
        addProductUseCase.addProductToCart(customer.getId(), product_id_1, 2);
        addProductUseCase.addProductToCart(customer.getId(), product_id_2, 3);
        addProductUseCase.addProductToCart(customer.getId(), product_id_1, 2);

        // THEN
        assertEquals(2, customer.getCart().getProducts().size());
        verify(saveCustomerCart, times(3)).saveCart(customer.getCart());
    }

    @Test()
    void add_product_to_order_should_save_cart() {
        // GIVEN
        var product_id_1 = UUID.randomUUID();
        var product1 = ProductItemMother.product1.build();
        var customer = CustomerMother.customer.build();

        given(retrieveProductItem.retrieve(product_id_1)).willReturn(product1);
        given(retrieveCustomer.retrieve(customer.getId())).willReturn(customer);

        // WHEN
        addProductUseCase.addProductToCart(customer.getId(), product_id_1, 1);

        // THEN
        verify(saveCustomerCart, times(1)).saveCart(customer.getCart());
    }

    @Test()
    void add_product_throw_product_not_exist() {
        // GIVEN
        var product_id_1 = UUID.randomUUID();
        var customer = CustomerMother.customer.build();

        doThrow(ProductNotFoundException.class)
                .when(retrieveProductItem)
                .retrieve(product_id_1);

        // WHEN
        assertThrows(ProductNotFoundException.class, () ->
                addProductUseCase.addProductToCart(customer.getId(), product_id_1, 1)
        );

        // THEN
        verify(retrieveCustomer, never()).retrieve(any());
        verify(saveCustomerCart, never()).saveCart(any());
    }

}