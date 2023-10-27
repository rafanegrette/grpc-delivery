package com.perficient.orderapp.infrastructure.adapter.out.persistence.repository;

import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CassandraOrderRepository extends CassandraRepository<OrderEntity, UUID> {
}
