package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {

    @Test
    public void productRequestToProduct() {
        // GIVEN
        var idProduct = UUID.randomUUID().toString();
        var productRequest = ProductRequest.newBuilder()
                .setProductId(idProduct)
                .setQuantity(5)
                .build();
        var productExpected = new Product(UUID.fromString(idProduct) , "Donas",5);
        // WHEN

        var productGiven = ProductMapper.INSTANCE.map(productRequest);

        // THEN

        assertNotNull(productGiven);
        assertEquals(productExpected, productGiven);
    }
}
