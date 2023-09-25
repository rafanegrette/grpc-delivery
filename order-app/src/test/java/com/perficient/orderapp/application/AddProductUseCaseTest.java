package com.perficient.orderapp.application;

import com.perficient.orderapp.domain.port.RetrieveOrder;
import com.perficient.orderapp.domain.port.RetrieveProductItem;
import com.perficient.orderapp.domain.port.SaveOrder;
import com.perficient.orderapp.domain.Order;
import com.perficient.orderapp.domain.OrderStatus;
import com.perficient.orderapp.domain.ProductItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddProductUseCaseTest {

    @Mock
    RetrieveProductItem retrieveProductItem;
    @Mock
    RetrieveOrder retrieveOrder;
    @Mock
    SaveOrder saveOrder;

    @InjectMocks
    AddProductUseCase addProductUseCase;

    @Test()
    void add_product_to_order_should_success() {
        // GIVEN
        var product_id_1 = UUID.randomUUID();
        var product_id_2 = UUID.randomUUID();
        var product1 = new ProductItem(product_id_1,
                "Salmon",
                "Fish",
                1,
                BigDecimal.TEN,
                BigDecimal.ZERO);
        var product2 = new ProductItem(product_id_2,
                "Rice",
                "Rice",
                1,
                BigDecimal.valueOf(4.3),
                BigDecimal.ZERO);
        var orderId = UUID.randomUUID();
        var order = Order.builder()
                .orderId(orderId)
                .customerId(UUID.randomUUID())
                .productItems(new HashSet<>())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();
        given(retrieveOrder.retrieve(orderId)).willReturn(order);
        given(retrieveProductItem.retrieve(product_id_1)).willReturn(product1);
        given(retrieveProductItem.retrieve(product_id_2)).willReturn(product2);

        // WHEN
        addProductUseCase.addProductToOrder(order, product_id_1, 2);
        addProductUseCase.addProductToOrder(order, product_id_2, 3);
        addProductUseCase.addProductToOrder(order, product_id_1, 2);

        // THEN
        assertEquals(2, order.getProductItems().size());
    }

    @Test()
    void add_product_to_order_should_verify_order() {
        // GIVEN
        var product_id_1 = UUID.randomUUID();
        var order_id = UUID.randomUUID();
        var order = Order.builder()
                .orderId(order_id)
                .customerId(UUID.randomUUID())
                .productItems(new HashSet<>())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();
        given(retrieveOrder.retrieve(order_id)).willReturn(order);
        // WHEN
        addProductUseCase.addProductToOrder(order, product_id_1, 1);

        // THEN
        verify(retrieveOrder, times(1)).retrieve(order_id);
    }

    @Test()
    void add_product_to_order_should_save_order() {
        // GIVEN
        var product_id_1 = UUID.randomUUID();
        var order_id = UUID.randomUUID();
        var order = Order.builder()
                .orderId(order_id)
                .customerId(UUID.randomUUID())
                .productItems(new HashSet<>())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .build();
        given(retrieveOrder.retrieve(order_id)).willReturn(order);
        // WHEN

        addProductUseCase.addProductToOrder(order, product_id_1, 1);

        // THEN
        verify(saveOrder, times(1)).save(order);
    }
}