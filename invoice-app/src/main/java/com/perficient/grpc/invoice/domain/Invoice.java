package com.perficient.grpc.invoice.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
  private UUID id;
  private String customerId;
  private String orderId;
  private double value;
  private Date paymentDate;
  private Boolean result;
  private Type typePay;

}
