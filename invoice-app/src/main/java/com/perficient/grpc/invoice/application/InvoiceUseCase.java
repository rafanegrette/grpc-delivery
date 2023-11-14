package com.perficient.grpc.invoice.application;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastructure.outputadapter.InvoiceRepository;
import org.springframework.stereotype.Component;

@Component
public class InvoiceUseCase implements InvoiceService {

  private final InvoiceRepository invoiceRepository;

  public InvoiceUseCase(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }

  private boolean validate(Invoice invoice) {
    return (invoice.getValue() >= 0);
  }

  @Override
  public Invoice createInvoice(Invoice invoice) {
    if(validate(invoice)){
      return this.invoiceRepository.saveInvoice(invoice);
    }
    invoice.setId("-1");
    return invoice;
  }
}
