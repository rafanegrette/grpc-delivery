package com.perficient.infrastructure.adapter.mapper;

import com.perficient.domain.model.Product;
import com.perficient.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.perficient.models.MenuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {

    Product entityToDomain (ProductEntity productEntity);

    @Mapping(source = "restaurantId", target = "menuId")
    @Mapping(source = "id", target = "productId")
    MenuResponse domainToResponse(Product product);

}
