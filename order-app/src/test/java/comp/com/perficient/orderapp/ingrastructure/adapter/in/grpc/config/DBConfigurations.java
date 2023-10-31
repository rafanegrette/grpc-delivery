package com.perficient.orderapp.ingrastructure.adapter.in.grpc.config;

import com.perficient.orderapp.infrastructure.adapter.out.persistence.config.CreateKeySpace;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class DBConfigurations {

    @MockBean
    public CassandraCustomerRepository cassandraCustomerRepository;
    @MockBean
    public CassandraCartRepository cassandraCartRepository;
    @MockBean
    CassandraOrderRepository cassandraOrderRepository;

    @MockBean
    CreateKeySpace createKeySpace;
}
