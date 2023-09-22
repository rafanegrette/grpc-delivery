package com.perficient.application.usecase;

import com.perficient.domain.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductDisplay {
    List<Product> displayProductsByRestaurant (int id);
    Product displayProductById(UUID id);

}
