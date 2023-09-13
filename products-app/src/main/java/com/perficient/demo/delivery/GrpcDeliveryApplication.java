package com.perficient.demo.delivery;

import com.perficient.infrastructure.adapter.in.grpc.FetchProducts;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FetchProducts.class)
public class GrpcDeliveryApplication {

	public static void main(String[] args) {

		SpringApplication.run(GrpcDeliveryApplication.class, args);
	}

}
