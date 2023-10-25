package com.perficient.invoice;

import com.perficient.grpc.invoice.infrastruture.outputport.EntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = MongoDataAutoConfiguration.class)
class InvoiceAppApplicationTests {

	@MockBean
	EntityRepository entityRepository;
    @Test
    void contextLoads() {
    }

}
