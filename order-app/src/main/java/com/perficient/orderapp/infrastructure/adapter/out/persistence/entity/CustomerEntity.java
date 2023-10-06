package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("Customer")
public record CustomerEntity (
    @PrimaryKey
    UUID customerId,
    String name,
    String address,
    UUID cartId) {

}
