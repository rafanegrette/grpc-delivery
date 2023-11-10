package com.perficient.grpc.invoice.application;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceMapper;
import com.perficient.grpc.invoice.infrastruture.outputadapter.InvoiceRepository;
import com.perficient.proto.invoice.InvoiceRequest;
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
