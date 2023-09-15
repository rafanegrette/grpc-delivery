package com.perficient.infrastructure.repository.out.cassandra;

import com.perficient.domain.model.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CassandraProductRepository  extends CassandraRepository<Product, UUID> {
}
