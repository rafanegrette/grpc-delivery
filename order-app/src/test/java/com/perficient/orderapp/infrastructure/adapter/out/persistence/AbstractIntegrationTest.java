package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@DataCassandraTest(
        properties = { "spring.cassandra.schema-action=create-if-not-exists",
                "spring.cassandra.connection.connect-timeout=60s",
                "spring.cassandra.connection.init-query-timeout=60s",
                "spring.cassandra.request.timeout=60s" }
)
@Import(AbstractIntegrationTest.KeyspaceTestConfiguration.class)
@Testcontainers(disabledWithoutDocker = true)
abstract class AbstractIntegrationTest {

    @Container
    @ServiceConnection
    static CassandraContainer<?> cassandra = new CassandraContainer<>(DockerImageName.parse("cassandra"));

    @Test
    void cassandra_running() {
        assertThat(cassandra.isRunning()).isTrue();
    }

    @TestConfiguration(proxyBeanMethods = false)
    static class KeyspaceTestConfiguration {

        @Bean
        CqlSession cqlSession(CqlSessionBuilder cqlSessionBuilder) {
            cqlSessionBuilder.withKeyspace((String)null);
            try (CqlSession session = cqlSessionBuilder.build()) {
                session.execute("CREATE KEYSPACE IF NOT EXISTS delivery_test"
                    + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
            }
            return cqlSessionBuilder.withKeyspace("delivery_test").build();
        }
    }
}
