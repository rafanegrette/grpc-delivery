package com.perficient.grpc.invoice.server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.proto.invoice.*;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl1 extends InvoiceServiceGrpc.InvoiceServiceImplBase {
  private InvoiceUseCase invoiceUseCase;

  public InvoiceServiceImpl1(InvoiceUseCase invoiceUseCase) {
    this.invoiceUseCase = invoiceUseCase;
  }

  private MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
  private MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
  private MongoCollection<Document> collection = mongoDatabase.getCollection("invoice");

  /*
  @Override
  public void invoice(InvoiceRequest request, StreamObserver<InvoiceResponse> responseObserver) {
    System.out.println("Received Create Invoice Request");
    Invoice invoice = request.getInvoice();
    this.invoiceUseCase.createInvoice(request);


    Document document = new Document("invoice_id", invoice.getInvoiceId())
        .append("client_id", invoice.getClient())
        .append("product_id", invoice.getProductId())
        .append("value", invoice.getValue());


    //we insert (create) the document in mondoDB
    collection.insertOne(document);

    //We retrieve the MongoDB generated ID
    String id = document.getObjectId("_id").toString();
    InvoiceResponse invoiceResponse = InvoiceResponse.newBuilder()
        .setInvoice(invoice.toBuilder()
            .setInvoiceId(id)
            .build())
            .setResult(true)
                .build();

    responseObserver.onNext(invoiceResponse);
    responseObserver.onCompleted();
  }


  @Override
  public void invoiceManyTimesServer(InvoiceManyTimesRequest request, StreamObserver<InvoiceManyTimesResponse> responseObserver) {
    String client = request.getClient();
    double value = request.getValue();
    int productId = request.getProductId();

    try {
      for(int i = 0; i < 10; i++){
        InvoiceManyTimesResponse invoiceManyTimesResponse = InvoiceManyTimesResponse
            .newBuilder()
            .setInvoice(Invoice.newBuilder().setInvoiceId("i"))
                .build();
               // .setCreationDate(Timestamp.newBuilder().setSeconds(i).build()))
            //.build();

        responseObserver.onNext(invoiceManyTimesResponse);
        Thread.sleep(1000L);
      }
    }catch (InterruptedException e){
      e.printStackTrace();
    }finally {
      responseObserver.onCompleted();
    }
  }
   */
}
