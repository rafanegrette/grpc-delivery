package com.perficient.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int customerId);
    List<Product> findAllBy(String query);
    void deleteById(int customerId);
    void save(Product product);
}
