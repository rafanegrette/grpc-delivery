package com.perficient.grpc.invoice.server;

import com.perficient.grpc.invoice.application.InvoiceUseCase;
import com.perficient.grpc.invoice.infrastruture.inputadapter.InvoiceInputAdapter;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceEntityMapperImpl;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceMapperImpl;
import com.perficient.grpc.invoice.infrastruture.outputadapter.InvoiceRepository;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.perficient.grpc.invoice")
public class InvoiceServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Invoice Test");

    Server server = ServerBuilder.forPort(50053)
        .addService(new InvoiceInputAdapter(new InvoiceUseCase(new InvoiceMapperImpl(), new InvoiceRepository(new InvoiceEntityMapperImpl()))))
        .build();
    server.start();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("Received Shutdown request");
      server.shutdown();
      System.out.println("Succesfully stopped the sever");
    }));
    System.out.println("Antes de terminar");
    // System.exit(0);
    server.awaitTermination();
  }
}
