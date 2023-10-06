package com.perficient.grpc.invoice.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

  Invoice invoice;
  @BeforeEach
  void setUp() {
    invoice = new Invoice();
  }

  @Test
  void getId() {
    UUID uuid = UUID.randomUUID();

    invoice.setId(uuid);

    assertEquals(uuid, invoice.getId());
  }

  @Test
  void getCustomerId() {
    String client = "idClient";

    invoice.setCustomerId(client);

    assertEquals(client, invoice.getCustomerId());
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

  }

  @Test
  void setCustomerId() {
  }

  @Test
  void setOrderId() {
  }

  @Test
  void setValue() {
  }

  @Test
  void setPaymentDate() {
  }

  @Test
  void setResult() {
  }

  @Test
  void setTypePay() {
  }
}