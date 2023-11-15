package com.perficient.grpc.invoice.domain;

public interface InvoiceRepository {
  Invoice saveInvoice(Invoice invoice);
}
