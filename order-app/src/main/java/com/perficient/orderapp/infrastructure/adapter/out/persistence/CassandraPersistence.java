package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CartEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.entity.CustomerEntity;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCartRepository;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraCustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@EnableCassandraRepositories(basePackages = "com.perficient.orderapp.infrastructure.adapter.out.persistence")
public class CassandraPersistence {


    private final CassandraCustomerRepository cassandraCustomerRepository;
    private final CassandraCartRepository cassandraCartRepository;

    // TODO remove this initializer
    @Bean
    public CommandLineRunner clr() {
        return args -> {
            UUID idUser = UUID.fromString("63d89df6-32bb-47ea-861c-a72605a833fe");
            UUID idCart = UUID.fromString("7834ec29-a784-40a7-b875-8b671a72c245");
            CustomerEntity customerEntity = new CustomerEntity(
                    idUser,
                    "TestUser",
                    "742 Evergreen Terrace",
                    idCart);
            cassandraCustomerRepository.save(customerEntity);

            CartEntity cartEntity = new CartEntity(
                    idCart,
                    new HashMap<>(),
                    BigDecimal.ZERO);
            cassandraCartRepository.save(cartEntity);
        };
    }


}
