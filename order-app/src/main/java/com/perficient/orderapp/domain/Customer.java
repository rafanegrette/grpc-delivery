package com.perficient.orderapp.domain;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class Customer {

    @NonNull
    private UUID id;

    @NonNull
    private String name;
    private String address;


}
