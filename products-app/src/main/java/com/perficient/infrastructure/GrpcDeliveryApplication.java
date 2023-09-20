package com.perficient.infrastructure;

import com.perficient.infrastructure.adapter.out.persistence.config.DataStaxAstraProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.nio.file.Path;


@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class GrpcDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcDeliveryApplication.class, args);
	}
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}
}

