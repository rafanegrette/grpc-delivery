package com.perficient;

import com.perficient.domain.model.Category;
import com.perficient.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.perficient.infrastructure.adapter.out.persistence.repository.CassandraProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootApplication
@EnableCassandraRepositories
public class GrpcDeliveryApplication {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(GrpcDeliveryApplication.class, args);
	}

	@Bean
	public CommandLineRunner clr(CassandraProductRepository cassandraProductRepository) {
		return args -> {
			List<ProductEntity> products = new ArrayList<>();
			cassandraProductRepository.deleteAll();

			ProductEntity p1 = new ProductEntity(UUID.randomUUID(), 1, "Cesar Salad", Category.SIDES, Boolean.TRUE, 25, 1);
			ProductEntity p2 = new ProductEntity(UUID.randomUUID(), 1, "lemon pie", Category.DESSERTS, Boolean.TRUE, 12, 1);
			ProductEntity p3 = new ProductEntity(UUID.randomUUID(), 1, "Salad", Category.SIDES, Boolean.TRUE, 20, 1);
			ProductEntity p4 = new ProductEntity(UUID.randomUUID(), 1, "Apple pie", Category.DESSERTS, Boolean.TRUE, 13, 1);
			ProductEntity p5 = new ProductEntity(UUID.randomUUID(), 1, "Corn Salad", Category.SIDES, Boolean.TRUE, 27, 1);
			ProductEntity p6 = new ProductEntity(UUID.randomUUID(), 1, "Grape pie", Category.DESSERTS, Boolean.TRUE, 15, 1);
			ProductEntity p7 = new ProductEntity(UUID.randomUUID(), 1, "Chicken Salad", Category.SIDES, Boolean.TRUE, 28, 1);
			ProductEntity p8 = new ProductEntity(UUID.randomUUID(), 1, "Peach pie", Category.DESSERTS, Boolean.TRUE, 12, 1);
			ProductEntity p9 = new ProductEntity(UUID.randomUUID(), 1, "Tuna Salad", Category.SIDES, Boolean.TRUE, 27, 1);
			ProductEntity p10 = new ProductEntity(UUID.randomUUID(), 1, "Fruits pie", Category.DESSERTS, Boolean.TRUE, 12, 1);


			products.add(p1); products.add(p2); products.add(p3); products.add(p4); products.add(p5);
			products.add(p6); products.add(p7); products.add(p8); products.add(p9); products.add(p10);

			cassandraProductRepository.saveAll(products);


			cassandraProductRepository.findAll()
					.forEach(p -> logger.info("Product: {}", p.getName()));

		};
	}

}

