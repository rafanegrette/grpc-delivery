package com.perficient.grpc.invoice.infrastructure.outputadapter;

import com.perficient.grpc.invoice.infrastructure.persistence.InvoiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends MongoRepository<InvoiceEntity, String> {

}
