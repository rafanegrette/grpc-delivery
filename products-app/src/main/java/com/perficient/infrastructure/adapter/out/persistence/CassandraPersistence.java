package com.perficient.infrastructure.adapter.out.persistence;

import com.perficient.application.usecase.ProductDisplay;
import com.perficient.domain.ProductRepository;
import com.perficient.domain.model.Product;
import com.perficient.infrastructure.adapter.mapper.ProductMapper;
import com.perficient.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.perficient.infrastructure.adapter.out.persistence.repository.CassandraProductRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CassandraPersistence implements ProductDisplay, ProductRepository {
    private final CassandraProductRepository cassandraProductRepository;

    private final ProductMapper productMapper;

    public CassandraPersistence(CassandraProductRepository cassandraProductRepository, ProductMapper productMapper) {
        this.cassandraProductRepository = cassandraProductRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<Product> displayProductsByRestaurant(int restaurantId) {
        return  findAllByRestaurant(restaurantId);
    }

    @Override
    public Product displayProductById(UUID id) {
        return findById(id);
    }

    @Override
    public List<Product> findAllByRestaurant(int restaurantId) {
        List<Product> productList = new ArrayList<>();
        List<ProductEntity> productEntityList = cassandraProductRepository.findProductEntityByRestaurantId(restaurantId);

        for (ProductEntity entity : productEntityList) {
            productList.add(productMapper.entityToDomain(entity));
        }

        return productList;
    }

    @Override
    public Product findById(UUID id) {
        return productMapper.entityToDomain(cassandraProductRepository.findById(id).get());
    }
}
