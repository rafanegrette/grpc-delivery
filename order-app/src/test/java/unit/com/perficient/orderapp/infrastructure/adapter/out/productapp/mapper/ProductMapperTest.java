package com.perficient.orderapp.infrastructure.adapter.out.productapp.mapper;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.MenuResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    @Test
    void map_menu_response_to_product_item() {
        // GIVEN
        var productId = UUID.randomUUID();
        var productName = "hot-dog";
        var category = "fast-food";
        var productExpected = new ProductItem(productId,
                productName,
                category,
                BigDecimal.TEN,
                BigDecimal.ZERO);
        var menuResponse = MenuResponse.newBuilder()
                .setMenuId(2)
                .setCategory(category)
                .setProductId(productId.toString())
                .setName(productName)
                .build();
        // WHEN
        var productReturned = ProductMapper.INSTANCE.map(menuResponse);
        // THEN

        assertEquals(productExpected, productReturned);
    }
}
