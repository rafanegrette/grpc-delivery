package com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper;

import com.perficient.orderapp.domain.Customer;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerEntityMapper {
    CustomerEntityMapper INSTANCE = Mappers.getMapper(CustomerEntityMapper.class);

    @Mapping(source = "id", target = "customerId")
    @Mapping(source = "cart.id", target = "cartId")
    CustomerEntity map(Customer customer);

    @Mapping(target  = "id", source = "customerId")
    @Mapping(target  = "cart.id", source = "cartId")
    Customer map(CustomerEntity customerEntity);
}
