package com.perficient.domain;

import com.perficient.domain.model.Product;
import java.util.List;


public interface ProductRepository {
    List<Product> findAllByRestaurant(int restaurantId);

}
