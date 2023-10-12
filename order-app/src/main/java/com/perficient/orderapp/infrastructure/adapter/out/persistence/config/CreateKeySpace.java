package com.perficient.orderapp.infrastructure.adapter.out.persistence.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CreateKeySpace {

    @Bean
    CqlSession cqlSession(CqlSessionBuilder cqlSessionBuilder) {
        cqlSessionBuilder.withKeyspace((String) null);
        try (CqlSession session = cqlSessionBuilder.build()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS order_keyspace"
                    + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
        }
        return cqlSessionBuilder.withKeyspace("order_keyspace").build();
    }

}
