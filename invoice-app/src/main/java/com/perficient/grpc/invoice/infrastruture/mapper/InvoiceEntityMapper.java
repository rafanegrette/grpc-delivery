package com.perficient.grpc.invoice.infrastruture.mapper;


import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.persistence.InvoiceEntity;
import com.perficient.proto.invoice.InvoiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface InvoiceEntityMapper {
  InvoiceEntityMapper INSTANCE = Mappers.getMapper(InvoiceEntityMapper.class);

  @Mapping(source = "clientId", target = "clientId")
  @Mapping(source = "orderId", target = "orderId")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "paymentDate", target = "paymentDate")
  @Mapping(source = "result", target = "result")
  InvoiceEntity toInvoiceEntity(Invoice invoice);


  @Mapping(source = "invoiceId", target = "id")
  @Mapping(source = "clientId", target = "clientId")
  @Mapping(source = "orderId", target = "orderId")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "paymentDate", target = "paymentDate")
  @Mapping(source = "result", target = "result")
  Invoice toInvoice(InvoiceEntity invoiceEntity);


  @Mapping(source = "invoice.clientId", target = "clientId")
  @Mapping(source = "invoice.orderId", target = "orderId")
  @Mapping(source = "invoice.value", target = "value")
  InvoiceEntity invoiceRequestToInvoiceEntity(InvoiceRequest invoiceRequest);



}
