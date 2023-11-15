package com.perficient.grpc.invoice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("server")
@Data
public class InvoiceConfiguration {
  private String port;
  private String name;
}
