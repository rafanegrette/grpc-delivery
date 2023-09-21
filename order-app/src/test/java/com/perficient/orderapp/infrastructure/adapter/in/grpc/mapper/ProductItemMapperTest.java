package com.perficient.orderapp.infrastructure.adapter.in.grpc.mapper;

import com.perficient.order.models.ProductRequest;
import com.perficient.orderapp.domain.model.ProductItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductItemMapperTest {

    @Test
    public void productRequestToProduct() {
        // GIVEN
        var idProduct = UUID.randomUUID().toString();
        var productRequest = ProductRequest.newBuilder()
                .setId(idProduct)
                .setQuantity(5)
                .build();
        var productExpected = new ProductItem(UUID.fromString(idProduct),
                "Donas",
                "Vegetables",
                5,
                BigDecimal.valueOf(4.6),
                BigDecimal.ZERO);

        // WHEN
/*
        var productGiven = ProductMapper.INSTANCE.map(productRequest);

        // THEN

        assertNotNull(productGiven);
        assertEquals(productExpected, productGiven);*/
    }
}
