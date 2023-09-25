package com.perficient.orderapp.infrastructure.adapter.out.persistence;

import com.perficient.orderapp.domain.model.Order;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.mapper.OrderEntityMapper;
import com.perficient.orderapp.infrastructure.adapter.out.persistence.repository.CassandraOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@EnableCassandraRepositories(basePackages = "com.perficient.orderapp.infrastructure.adapter.out.persistence")
public class CassandraOrderPersistence {

    private final CassandraOrderRepository orderRepository;

    public void save(Order order) {
        var orderEntity = OrderEntityMapper.INSTANCE.map(order);
        orderRepository.save(orderEntity);
    }

    public Order retrieve(UUID orderId) {
        return null;
    }

}
