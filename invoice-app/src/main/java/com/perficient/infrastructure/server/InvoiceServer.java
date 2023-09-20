package com.perficient.infrastructure.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class InvoiceServer {
  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Invoice Test");

    Server server = ServerBuilder.forPort(50052)
        .addService(new InvoiceServiceImpl())
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