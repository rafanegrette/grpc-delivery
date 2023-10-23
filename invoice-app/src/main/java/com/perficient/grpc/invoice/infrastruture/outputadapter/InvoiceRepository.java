package com.perficient.grpc.invoice.infrastruture.outputadapter;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.mapper.InvoiceEntityMapper;
import com.perficient.grpc.invoice.infrastruture.outputport.EntityRepository;
import com.perficient.grpc.invoice.infrastruture.persistence.InvoiceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.logging.Logger;

@Repository
public class InvoiceRepository {
  private static final Logger logger = Logger.getLogger(InvoiceRepository.class.getName());
  private final InvoiceEntityMapper invoiceEntityMapper;
  private final EntityRepository entityRepository;

  public InvoiceRepository(InvoiceEntityMapper invoiceEntityMapper, EntityRepository entityRepository) {
    this.invoiceEntityMapper = invoiceEntityMapper;
    this.entityRepository = entityRepository;
  }

  public Invoice saveInvoice(Invoice invoice){
    InvoiceEntity invoiceEntity = this.invoiceEntityMapper.toInvoiceEntity(invoice);
    this.entityRepository.save(invoiceEntity);
    InvoiceEntity entity = this.entityRepository.findAll(Sort.by(Sort.Order.desc("paymentDate"))).get(0);
    invoice.setId(entity.getInvoiceId());
    invoice.setResult(true);
    return invoice;
  }
}
