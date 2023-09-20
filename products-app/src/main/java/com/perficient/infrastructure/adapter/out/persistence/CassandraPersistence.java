package com.perficient.infrastructure.adapter.out.persistence;

import com.perficient.application.usecase.ProductDisplay;
import com.perficient.domain.ProductRepository;
import com.perficient.domain.model.Product;
import com.perficient.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.perficient.infrastructure.adapter.out.persistence.repository.CassandraProductRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CassandraPersistence implements ProductDisplay, ProductRepository {
    private final CassandraProductRepository cassandraProductRepository;

    public CassandraPersistence(CassandraProductRepository cassandraProductRepository) {
        this.cassandraProductRepository = cassandraProductRepository;
    }


    @Override
    public List<Product> displayProductsByRestaurant(int restaurantId) {
       return  findAllByRestaurant(restaurantId);
    }

    @Override
    public List<Product> findAllByRestaurant(int restaurantId) {
        List<Product> productList = new ArrayList<>();
        List<ProductEntity> productEntityList = cassandraProductRepository.findProductEntityByRestaurantId(restaurantId);

        //TODO mapper
        for (ProductEntity entity : productEntityList) {
            Product productBuild = new Product(entity.getId(), entity.getRestaurantId(), entity.getName(), entity.getCategory(),
                    entity.getAvailable(), entity.getPrice(), entity.getDiscount());
            productList.add(productBuild);
        }

        return productList;
    }
}
