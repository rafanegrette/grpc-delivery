package com.perficient.orderapp.domain.model;

import lombok.NonNull;
import lombok.With;

import java.util.UUID;

public record Customer (
        @NonNull
        UUID id,
        @NonNull
        String name,
        String address) {

    public Customer (UUID id, String name) {
        this(id, name, "");
    }
}
