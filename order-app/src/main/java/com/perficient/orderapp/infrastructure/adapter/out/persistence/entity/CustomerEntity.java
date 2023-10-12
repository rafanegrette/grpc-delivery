package com.perficient.orderapp.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("Customer")
public class CustomerEntity {
    @PrimaryKey
    private UUID customerId;
    private String name;
    private String address;
    private UUID cartId;

}
