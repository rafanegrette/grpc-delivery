package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Arrays;
import java.util.List;

//@Configuration
public class CreateKeyspaceConfiguration extends AbstractCassandraConfiguration
                                        implements BeanClassLoaderAware {

    public static final String ORDER_KEYSPACE = "order_keyspace";
    public static final String DATACENTER = "datacenter1";

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        try {
            CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
                    .with(KeyspaceOption.DURABLE_WRITES, true)
                    .withNetworkReplication(DataCenterReplication.of(DATACENTER, 1));
            return Arrays.asList(specification);
        } catch (Exception e) {
            return Arrays.asList();
        }

    }
    @Override
    protected String getKeyspaceName() {
        return ORDER_KEYSPACE;
    }
}
