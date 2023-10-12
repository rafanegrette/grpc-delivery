package com.perficient.orderapp.infrastructure.adapter.out.persistence;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration(proxyBeanMethods = false)
class KeyspaceTestConfiguration {

    @Bean
    CqlSession cqlSession(CqlSessionBuilder cqlSessionBuilder) {
        cqlSessionBuilder.withKeyspace((String) null);
        try (CqlSession session = cqlSessionBuilder.build()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS delivery_test"
                    + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
        }
        return cqlSessionBuilder.withKeyspace("delivery_test").build();
    }
}
