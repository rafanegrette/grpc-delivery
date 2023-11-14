package com.perficient.invoiceapp.domain;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.domain.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

  Invoice invoice;

  @BeforeEach
  void setUp() {
    invoice = new Invoice();
  }

  @Test
  void getCustomerId() {
    String client = "idClient";

    invoice.setClientId(client);

    assertEquals(client, invoice.getClientId());
  }

  @Test
  void getOrderId() {
    String order = "idOrder";

    invoice.setOrderId(order);

    assertEquals(order, invoice.getOrderId());
  }

  @Test
  void getValue() {
    double value = 193;

    invoice.setValue(value);

    assertEquals(value, invoice.getValue());
  }

  @Test
  void getPaymentDate() {
    Date date = new Date();

    invoice.setPaymentDate(date);

    assertEquals(date, invoice.getPaymentDate());
  }

  @Test
  void getResult() {
    boolean result = true;

    invoice.setResult(result);

    assertEquals(result, invoice.getResult());
  }

  @Test
  void getTypePay() {
    Type type = Type.CREDIT;

    invoice.setTypePay(type);

    assertEquals(type, invoice.getTypePay());
  }

  @Test
  void setId() {
    String id = "test";

    invoice.setId(id);

    assertEquals(id, invoice.getId());
  }

  @Test
  void setCustomerId() {
    String customerId = "test";

    invoice.setClientId(customerId);

    assertEquals(customerId, invoice.getClientId());
  }

  @Test
  void setOrderId() {
    String orderId = "test";

    invoice.setOrderId(orderId);

    assertEquals(orderId, invoice.getOrderId());
  }

  @Test
  void setValue() {
    long value = 112233;

    invoice.setValue(value);

    assertEquals(value, invoice.getValue());
  }

  @Test
  void setPaymentDate() {
    Date date = new Date();

    invoice.setPaymentDate(date);

    assertEquals(date, invoice.getPaymentDate());
  }

  @Test
  void setResult() {
    boolean result = true;

    invoice.setResult(result);

    assertEquals(result, invoice.getResult());
  }

  @Test
  void setTypePay() {
    Type type = Type.CREDIT;

    invoice.setTypePay(type);

    assertEquals(type, invoice.getTypePay());
  }
}