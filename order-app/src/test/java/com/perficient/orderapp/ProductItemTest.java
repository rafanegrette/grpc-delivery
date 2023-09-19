package com.perficient.orderapp;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.exception.InvalidProductItemException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class ProductItemTest {

    @Test
    public void testProductItem() throws InvalidProductItemException {

        UUID id = UUID.randomUUID();
        ProductItem productItem = new ProductItem(id, "Donut", "Food", -3, 2.5, 0);
        productItem.setQuantity(-5);
        assertEquals("", productItem.getId(), id);

    }

}
