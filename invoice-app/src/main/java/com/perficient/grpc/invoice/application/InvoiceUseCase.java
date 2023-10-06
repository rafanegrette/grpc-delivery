package com.perficient.grpc.invoice.application;

import com.perficient.grpc.invoice.application.Exceptions.CustomException;
import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceMapper;
import com.perficient.grpc.invoice.infrastruture.outputadapter.InvoiceRepository;
import com.perficient.proto.invoice.InvoiceRequest;
import org.springframework.stereotype.Component;

@Component
public class InvoiceUseCase implements InvoiceService{
  private final InvoiceMapper invoiceMapper;
  private final InvoiceRepository invoiceRepository;

  public InvoiceUseCase(InvoiceMapper invoiceMapper, InvoiceRepository invoiceRepository) {
    this.invoiceMapper = invoiceMapper;
    this.invoiceRepository = invoiceRepository;
  }
  private void validate(Invoice invoice){
    if(invoice.getValue() == 0 || invoice.getCustomerId().isEmpty()){
      throw new CustomException("P-401", "The id of the customer is invalid");
    }
  }
  @Override
  public Invoice createInvoice(InvoiceRequest request) {
    Invoice invoice =  this.invoiceMapper.toEntity(request);
    validate(invoice);
    return this.invoiceRepository.saveInvoice(invoice);
  }
}
