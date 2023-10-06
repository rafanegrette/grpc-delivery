package com.perficient.grpc.invoice.infrastruture.mapper;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.persistence.InvoiceEntity;
import com.perficient.proto.invoice.InvoiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface InvoiceMapper {
  InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

 // @Mapping(source = "id", target = "id")
  @Mapping(source = "invoice.client", target = "customerId")
  @Mapping(source = "invoice.orderId", target = "orderId")
  @Mapping(source = "invoice.value", target = "value")
  Invoice toEntity(InvoiceRequest request);
  Invoice toInvoice(InvoiceEntity invoiceEntity);


}
