package com.perficient.infrastructure.repository.out.cassandra;

import com.perficient.domain.model.Product;
import com.perficient.domain.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CassandraPersistence implements ProductRepository {
    private final CassandraProductRepository cassandraProductRepository;

    public CassandraPersistence(CassandraProductRepository cassandraProductRepository) {
        this.cassandraProductRepository = cassandraProductRepository;
    }

    @Override
    public Optional<Product> findById(int customerId) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAllBy(String query) {
        return cassandraProductRepository.findAll();
    }

    @Override
    public void deleteById(int customerId) {

    }

    @Override
    public void save(Product product) {

    }
}
