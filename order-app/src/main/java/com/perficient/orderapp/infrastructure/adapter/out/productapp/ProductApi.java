package com.perficient.orderapp.infrastructure.adapter.out.productapp;

import com.perficient.orderapp.domain.ProductItem;
import com.perficient.orderapp.domain.excepton.ProductNotFoundException;
import com.perficient.orderapp.domain.port.RetrieveProductItem;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.mapper.ProductMapper;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.FetchProductsGrpc.FetchProductsBlockingStub;
import com.perficient.orderapp.infrastructure.adapter.out.productapp.model.ProductRequest;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ProductApi implements RetrieveProductItem {

    private final FetchProductsBlockingStub productsGrpcApi;

    public ProductApi(FetchProductsBlockingStub productsGrpcApi) {
        this.productsGrpcApi = productsGrpcApi;
    }

    @Override
    public ProductItem retrieve(UUID productId) {
        var productRequest = ProductRequest.newBuilder()
                .setProductId(productId.toString())
                .build();
        try {
            var menuResponse = productsGrpcApi.getProduct(productRequest);
            return ProductMapper.INSTANCE.map(menuResponse);
        } catch (StatusRuntimeException ex) {
            log.error("Exception in Product Application: ", ex.getMessage());
            throw new ProductNotFoundException("Product id: "+ productId + ", not found");
        }

        //return new ProductItem(productId, "Beans", "Vegetables", BigDecimal.valueOf(2.5), BigDecimal.ZERO);
    }
}
