package com.perficient.grpc.invoice.infrastruture.persistence;


import com.perficient.grpc.invoice.application.Exceptions.CustomException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

//@Getter
//@Setter
@Document(collection = "invoices")
public class InvoiceEntity {
  @Id
  private String invoiceId;
  private String clientId;

  private String orderId;
  private double value;
  @Field("paymentDate")
  private Date paymentDate;
  private Boolean result;

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
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
