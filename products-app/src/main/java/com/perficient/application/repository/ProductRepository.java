package com.perficient.application.repository;

import com.perficient.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll(int id);
}
