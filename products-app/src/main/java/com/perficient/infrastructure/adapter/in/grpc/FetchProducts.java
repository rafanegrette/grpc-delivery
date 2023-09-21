package com.perficient.infrastructure.adapter.in.grpc;

import com.perficient.models.FetchProductsGrpc;
import com.perficient.models.MenuRequest;
import com.perficient.models.MenuResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FetchProducts  extends FetchProductsGrpc.FetchProductsImplBase {

    private final BuildMenuResponse buildMenuResponse;

    public FetchProducts(BuildMenuResponse buildMenuResponse) {
        this.buildMenuResponse = buildMenuResponse;
    }

    @Override
    public void productsStream(MenuRequest request, StreamObserver<MenuResponse> responseObserver){
        buildMenuResponse.buildResponse(responseObserver, request.getMenuId());
        responseObserver.onCompleted();
    }

}
