package com.perficient.orderapp.domain.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

public record Order (@NonNull String orderId,
                     @NonNull Customer customer,
                     List<Product> products) {


}
