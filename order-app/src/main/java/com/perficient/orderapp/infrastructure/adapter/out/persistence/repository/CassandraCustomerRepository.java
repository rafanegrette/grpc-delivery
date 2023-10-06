package com.perficient.orderapp.infrastructure.adapter.out.persistence.repository;

import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CassandraCustomerRepository extends CassandraRepository<CustomerEntity, UUID> {
}
