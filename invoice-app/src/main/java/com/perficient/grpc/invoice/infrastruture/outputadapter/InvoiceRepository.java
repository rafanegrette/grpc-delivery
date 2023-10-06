package com.perficient.grpc.invoice.infrastruture.outputadapter;



import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceEntityMapper;
import com.perficient.grpc.invoice.infrastruture.persistence.InvoiceEntity;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public class InvoiceRepository {

  private final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
  private final MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
  private final MongoCollection<Document> collection = mongoDatabase.getCollection("invoice");
  private final InvoiceEntityMapper invoiceEntityMapper;
  public InvoiceRepository(InvoiceEntityMapper invoiceEntityMapper) {
    this.invoiceEntityMapper = invoiceEntityMapper;
  }
  public Invoice saveInvoice(Invoice invoice){
    InvoiceEntity invoiceEntity = this.invoiceEntityMapper.toInvoiceEntity(invoice);
    Document document = new Document("client_id", invoiceEntity.getCustomerId())
        .append("order_id", invoiceEntity.getOrderId())
        .append("value", invoiceEntity.getValue());
    //we insert (create) the document in mondoDB
    collection.insertOne(document);
    //We retrieve the MongoDB generated ID
    String id = document.getObjectId("_id").toString();
    invoice.setId(UUID.randomUUID());
    invoice.setResult(true);
    return invoice;
  }
}
