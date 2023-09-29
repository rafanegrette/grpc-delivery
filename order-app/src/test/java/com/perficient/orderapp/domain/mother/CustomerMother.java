package com.perficient.orderapp.domain.mother;

import com.perficient.orderapp.domain.Customer;

import java.util.UUID;

public class CustomerMother {

    public static UUID customerId = UUID.randomUUID();
    public static CustomerBuilder customer = new CustomerBuilder()
            .id(customerId)
            .name("ralph")
            .address("742 Evergreen Terrace");

    public static class CustomerBuilder {
        private UUID id;
        private String name;
        private String address;

        public CustomerBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }
        public CustomerBuilder address(String address) {
            this.address = address;
            return this;
        }
        public Customer build() {
            return new Customer(id, name, address);
        }
    }
}
