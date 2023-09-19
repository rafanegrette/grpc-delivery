package com.perficient.orderapp.domain.port;

import com.perficient.orderapp.domain.ProductItem;

import java.util.UUID;

public interface ProductPort {
    ProductItem getProductById(UUID id);
}
