package com.perficient.orderapp;

import com.perficient.orderapp.configuration.security.GrpcSecurityConfiguration;
import com.perficient.orderapp.configuration.security.JwtConfiguration;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.config.CreateKeySpace;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import org.junit.jupiter.api.Test;
import org.lognet.springboot.grpc.security.GrpcSecurity;
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter;
import org.lognet.springboot.grpc.security.jwt.JwtAuthProviderFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@WithMockUser
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

	@MockBean
	GrpcSecurityConfiguration grpcSecurityConfiguration;
	@MockBean
	JwtConfiguration jwtConfiguration;

	@Test
	void contextLoads() {
	}


	@TestConfiguration
	static class TestCfg extends GrpcSecurityConfigurerAdapter {

		@MockBean
		private JwtDecoder jwtDecoder;

		@Override
		public void configure(GrpcSecurity builder) throws Exception {
			builder.authorizeRequests()
					.anyMethod()
					.authenticated()
					.and()
					.authenticationProvider(JwtAuthProviderFactory
							.forAuthorities(getContext().getBean(JwtDecoder.class)));
		}
	}
}
