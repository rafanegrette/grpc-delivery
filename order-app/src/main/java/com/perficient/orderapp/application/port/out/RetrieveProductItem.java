package com.perficient.orderapp.application.port.out;

import com.perficient.orderapp.domain.model.ProductItem;

import java.util.UUID;

public interface RetrieveProductItem {

    ProductItem retrieve(UUID productId);
}
