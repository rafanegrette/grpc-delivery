package com.perficient.grpc.invoice.infrastructure.outputadapter;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.domain.InvoiceRepository;
import com.perficient.grpc.invoice.infrastructure.mapper.InvoiceEntityMapper;
import com.perficient.grpc.invoice.infrastructure.persistence.InvoiceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepository implements InvoiceRepository {
  private final InvoiceEntityMapper invoiceEntityMapper;
  private final EntityRepository entityRepository;

  public MongoRepository(InvoiceEntityMapper invoiceEntityMapper, EntityRepository entityRepository) {
    this.invoiceEntityMapper = invoiceEntityMapper;
    this.entityRepository = entityRepository;
  }

  @Override
  public Invoice saveInvoice(Invoice invoice) {
    InvoiceEntity invoiceEntity = this.invoiceEntityMapper.toInvoiceEntity(invoice);
    this.entityRepository.save(invoiceEntity);
    InvoiceEntity entity = this.entityRepository.findAll(Sort.by(Sort.Order.desc("paymentDate"))).get(0);
    invoice.setId(entity.getInvoiceId());
    invoice.setResult(true);
    return invoice;
  }
}
