package com.perficient.infrastructure.adapter.out.persistence.entity;

import com.perficient.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class ProductEntity {
        @PrimaryKey
        private UUID id;

        @Column("restaurantid")
        @CassandraType(type = CassandraType.Name.INT)
        private int restaurantId;

        @Column("name")
        @CassandraType(type = CassandraType.Name.TEXT)
        private String name;

        @Column("category")
        @CassandraType(type = CassandraType.Name.TEXT)
        private Category category;

        @Column("available")
        @CassandraType(type = CassandraType.Name.BOOLEAN)
        private Boolean available;

        @Column("price")
        @CassandraType(type = CassandraType.Name.INT)
        private int price;

        @Column("discount")
        @CassandraType(type = CassandraType.Name.FLOAT)
        private float discount;
}
