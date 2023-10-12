package com.perficient.grpc.invoice.infrastruture.persistence;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

//@Getter
//@Setter
@Document(collection = "invoices")
public class InvoiceEntity {
  @Id
  private UUID idInvoice;
  private String customerId;

  private String orderId;
  private double value;
  private Date paymentDate;
  private Boolean result;

  public UUID getIdInvoice() {
    return idInvoice;
  }

  public void setIdInvoice(UUID idInvoice) {
    this.idInvoice = idInvoice;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
  public Date getPaymentDate() {
    return paymentDate;
  }
  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }
  public Boolean getResult() {
    return result;
  }
  public void setResult(Boolean result) {
    this.result = result;
  }
}
