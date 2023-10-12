package com.perficient.orderapp;

import com.perficient.orderapp.infrastructure.adapter.out.persistence.config.CreateKeySpace;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = CassandraDataAutoConfiguration.class)
class OrderAppApplicationTests {

	@MockBean
	CassandraCustomerRepository cassandraCustomerRepository;
	@MockBean
	CassandraCartRepository cassandraCartRepository;
	@MockBean
	CassandraOrderRepository cassandraOrderRepository;

	@MockBean
	CreateKeySpace createKeySpace;

	@Test
	void contextLoads() {
	}

}
