package com.perficient.grpc.invoice.application;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceMapper;
import com.perficient.grpc.invoice.infrastruture.outputadapter.InvoiceRepository;
import com.perficient.proto.invoice.InvoiceRequest;
import org.springframework.stereotype.Component;

@Component
public class InvoiceUseCase implements InvoiceService {
  private final InvoiceMapper invoiceMapper;
  private final InvoiceRepository invoiceRepository;

  public InvoiceUseCase(InvoiceMapper invoiceMapper, InvoiceRepository invoiceRepository) {
    this.invoiceMapper = invoiceMapper;
    this.invoiceRepository = invoiceRepository;
  }

  private boolean validate(Invoice invoice) {
    return (invoice.getValue() >= 0);
  }

  @Override
  public Invoice createInvoice(InvoiceRequest request) {
    //Llega invoice
    //Este mapper debe estar    (infra no debe estar en el dominio)
    Invoice invoice = this.invoiceMapper.toEntity(request);
    if(validate(invoice)){
      return this.invoiceRepository.saveInvoice(invoice);
    }
    invoice.setId("-1");
    return invoice;
  }
}
