package com.perficient.grpc.invoice.infrastruture.mapper;

import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.proto.invoice.InvoiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface InvoiceResponseMapper {
  InvoiceResponseMapper INSTANCE = Mappers.getMapper(InvoiceResponseMapper.class);

  @Mapping(source = "invoice.id", target = "invoice.invoiceId")
  @Mapping(source = "invoice.clientId", target = "invoice.clientId")
  @Mapping(source = "invoice.orderId", target = "invoice.orderId")
  @Mapping(source = "invoice.value", target = "invoice.value")
  InvoiceResponse toInvoiceResponse(Invoice invoice);
}
