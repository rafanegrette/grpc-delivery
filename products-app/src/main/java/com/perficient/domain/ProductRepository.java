package com.perficient.domain;

import com.perficient.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int productId);
    List<Product> findAllBy(String query);
    void deleteById(int productId);
    void save(Product product);
}
