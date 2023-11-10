package com.perficient.grpc.invoice.application;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.proto.invoice.InvoiceRequest;

public interface InvoiceService {
  Invoice createInvoice(Invoice request);
}
