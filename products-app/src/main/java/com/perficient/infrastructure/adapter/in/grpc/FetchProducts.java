package com.perficient.infrastructure.adapter.in.grpc;

import com.perficient.models.FetchProductsGrpc;
import com.perficient.models.MenuRequest;
import com.perficient.models.MenuResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FetchProducts extends FetchProductsGrpc.FetchProductsImplBase {

    @Override
    public void products(MenuRequest request, StreamObserver<MenuResponse> responseObserver) {
        int menuId = request.getMenuId();

        MenuResponse menuResponse = MenuResponse.newBuilder()
                .setMenuId(menuId)
                .setProductId(1)
                .setName("Pasta risotto")
                .setPrice(28000)
                .build();
        responseObserver.onNext(menuResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void productsStream(MenuRequest request, StreamObserver<MenuResponse> responseObserver){
        int menuId = request.getMenuId();

        for (int i = 0; i < 3; i++) {
            MenuResponse menuResponse = MenuResponse.newBuilder()
                    .setMenuId(menuId)
                    .setProductId(i+1)
                    .setName("Pasta risotto")
                    .setPrice(28000)
                    .build();
            responseObserver.onNext(menuResponse);
        }
        responseObserver.onCompleted();
    }

}
