package com.perficient.grpc.invoice.infrastruture.outputport;



import com.perficient.grpc.invoice.infrastruture.persistence.InvoiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends MongoRepository<InvoiceEntity, String> {

}
