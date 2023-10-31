package com.perficient.orderapp.domain;

import com.perficient.orderapp.domain.excepton.InvalidProductItemException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static com.perficient.orderapp.domain.ProductItem.ERROR_PRICE_LESS_THAN_0;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductItemTest {

    @Test
    public void testCreateProduct_ThrowInvalidProductItem() {
        UUID id = UUID.randomUUID();
        var exception = assertThrows(InvalidProductItemException.class, () ->
                new ProductItem(id,
                        "Donut",
                        "Food",
                        BigDecimal.valueOf(-2.5),
                        BigDecimal.ZERO));

        assertEquals(ERROR_PRICE_LESS_THAN_0, exception.getMessage());
    }
}