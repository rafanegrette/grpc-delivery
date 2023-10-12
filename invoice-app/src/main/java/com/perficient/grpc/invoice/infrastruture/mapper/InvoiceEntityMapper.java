package com.perficient.grpc.invoice.infrastruture.mapper;


import com.perficient.grpc.invoice.domain.Invoice;
import com.perficient.grpc.invoice.infrastruture.persistence.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface InvoiceEntityMapper {
  InvoiceEntityMapper INSTANCE = Mappers.getMapper(InvoiceEntityMapper.class);

 // @Mapping(source = "id", target = "idInvoice")
  @Mapping(source = "customerId", target = "customerId")
  @Mapping(source = "orderId", target = "orderId")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "paymentDate", target = "paymentDate")
  @Mapping(source = "result", target = "result")
  InvoiceEntity toInvoiceEntity(Invoice invoice);


  @Mapping(source = "idInvoice", target = "id")
  @Mapping(source = "customerId", target = "customerId")
  @Mapping(source = "orderId", target = "orderId")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "paymentDate", target = "paymentDate")
  @Mapping(source = "result", target = "result")
  Invoice toInvoice(InvoiceEntity invoiceEntity);




  /*
  @Mapping(source = "id", target = "customerId")
  @Mapping(source = "cart.id", target = "cartId")
  CustomerEntity map(Customer customer);

  @Mapping(target  = "id", source = "customerId")
  @Mapping(target  = "cart.id", source = "cartId")
  Customer map(CustomerEntity customerEntity);
*/




  //InvoiceEntity toEntity(Invoice invoice);

  /*
  InvoiceRequest toInvoiceRequest(InvoiceEntity invoiceEntity);
  Invoice toDomain(InvoiceEntity invoiceEntity);

   */


}
