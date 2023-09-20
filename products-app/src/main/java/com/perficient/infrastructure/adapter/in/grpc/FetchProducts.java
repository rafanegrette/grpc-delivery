package com.perficient.infrastructure.adapter.in.grpc;

import com.perficient.domain.model.Product;
import com.perficient.infrastructure.adapter.out.persistence.CassandraPersistence;
import com.perficient.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.perficient.models.FetchProductsGrpc;
import com.perficient.models.MenuRequest;
import com.perficient.models.MenuResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class FetchProducts  extends FetchProductsGrpc.FetchProductsImplBase {

    private final CassandraPersistence cassandraPersistence;



    public FetchProducts(CassandraPersistence cassandraPersistence) {
        this.cassandraPersistence = cassandraPersistence;
    }


    @Override
    public void products(MenuRequest request, StreamObserver<MenuResponse> responseObserver) {
        int menuId = request.getMenuId();

        MenuResponse menuResponse = MenuResponse.newBuilder()
                .setMenuId(menuId)
                .setProductId("1")
                .setName("Pasta risotto")
                .setPrice(28000)
                .build();
        responseObserver.onNext(menuResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void productsStream(MenuRequest request, StreamObserver<MenuResponse> responseObserver){
        int restaurantId = request.getMenuId();

        List<Product> allProducts = cassandraPersistence.displayProductsByRestaurant(restaurantId);

        //TODO the operation below doesn't belong to this class
        for (int i = 0; i <= allProducts.size()-1; i++) {

            MenuResponse menuResponse = MenuResponse.newBuilder()
                    .setMenuId(allProducts.get(i).getRestaurantId())
                    .setName(allProducts.get(i).getName())
                    .setCategory(allProducts.get(i).getCategory().toString())
                    .setPrice(allProducts.get(i).getPrice())
                    .build();
            responseObserver.onNext(menuResponse);
        }
        responseObserver.onCompleted();
    }
}
