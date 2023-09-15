package com.perficient.application.usecase;

import com.perficient.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDisplay {
    List<Product> displayAllBy (String query);
    Optional<Product> displayById(int productId);
}
