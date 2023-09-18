package com.perficient.application.usecase;

import com.perficient.domain.model.Product;
import java.util.List;

public interface ProductDisplay {
    List<Product> displayProductsByRestaurant (int id);

}
