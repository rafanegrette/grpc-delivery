package com.perficient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.perficient.grpc.invoice")
public class InvoiceAppApplication {
  public static void main(String[] args) {
    SpringApplication.run(InvoiceAppApplication.class, args);
  }
}