package com.perficient.grpc.invoice.domain;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
  private String id;
  private String clientId;
  private String orderId;
  private String requestId;
  private double value;
  private Date paymentDate;
  private Boolean result;
  private Type typePay;

}
