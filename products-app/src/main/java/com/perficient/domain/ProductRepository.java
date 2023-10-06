package com.perficient.domain;

import com.perficient.domain.model.Product;
import java.util.List;
import java.util.UUID;


public interface ProductRepository {
    List<Product> findAllByRestaurant(int restaurantId);
    Product findById(UUID id);

}
