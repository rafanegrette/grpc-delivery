package com.perficient.infrastructure.adapter.in.grpc;

import com.perficient.domain.model.Product;
import com.perficient.infrastructure.adapter.mapper.ProductMapper;
import com.perficient.infrastructure.adapter.out.persistence.CassandraPersistence;
import com.perficient.models.MenuResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
class BuildMenuResponse {

    private final CassandraPersistence cassandraPersistence;
    private final ProductMapper productMapper;

    public BuildMenuResponse(CassandraPersistence cassandraPersistence, ProductMapper productMapper) {
        this.cassandraPersistence = cassandraPersistence;
        this.productMapper = productMapper;
    }

    void buildProductsByRestaurant(StreamObserver<MenuResponse> responseObserver, int restaurantId) {
        List<Product> allProducts = cassandraPersistence.displayProductsByRestaurant(restaurantId);

        for (int i = 0; i <= allProducts.size()-1; i++) {
            responseObserver.onNext(productMapper.domainToResponse(allProducts.get(i)));
        }
    }

    public void buildProductById(StreamObserver<MenuResponse> responseObserver, String productId) {
        Product product = cassandraPersistence.displayProductById(UUID.fromString(productId));
        responseObserver.onNext(productMapper.domainToResponse(product));
    }
}
