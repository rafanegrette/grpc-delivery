package com.perficient.orderapp.infrastructure.adapter.out.persistence.repository;

import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CartEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CassandraCartRepository extends CassandraRepository<CartEntity, UUID> {
}
