package com.perficient.orderapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Customer {
        @NonNull
        private UUID id;
        @NonNull
        private String name;
        private String address;

    public Customer (UUID id, String name) {
        this(id, name, "");
    }
}
