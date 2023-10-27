package com.perficient.orderapp.infrastructure.adapter.out.productapp;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.excepton.ProductNotFoundException;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc.FetchProductsBlockingStub;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.MenuResponse;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.ProductRequest;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductApiTest {

    @Mock
    FetchProductsBlockingStub productsGrpcApi;

    @InjectMocks
    ProductApi productApi;

    @Test
    void retrieve_product_should_success() {
        // GIVEN
        var productId = UUID.randomUUID();
        var productName = "hot-dog";
        var category = "fast-food";
        var productExpected = new ProductItem(productId,
                productName,
                "fast-food",
                BigDecimal.TEN,
                BigDecimal.ZERO);
        var menuResponse = MenuResponse.newBuilder()
                .setMenuId(2)
                .setCategory(category)
                .setProductId(productId.toString())
                .setName(productName)
                .build();
        var productRequest = ProductRequest.newBuilder()
                .setProductId(productId.toString())
                .build();

        when(productsGrpcApi.getProduct(productRequest)).thenReturn(menuResponse);
        // WHEN
        var productReturned = productApi.retrieve(productId);
        // THEN
        assertEquals(productExpected, productReturned);
    }

    @Test
    void retrieve_product_should_throw_product_not_found() {
        // GIVEN
        var productId = UUID.randomUUID();

        doThrow(StatusRuntimeException.class)
                .when(productsGrpcApi)
                .getProduct(any(ProductRequest.class));

        // WHEN
        ProductNotFoundException productNotFound = assertThrows(ProductNotFoundException.class, () ->
                productApi.retrieve(productId)
        );
        // THEN
        assertEquals("Product id: " + productId + ", not found", productNotFound.getMessage());
    }
}
