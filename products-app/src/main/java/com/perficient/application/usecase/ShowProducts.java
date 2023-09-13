package com.perficient.application.usecase;

import com.perficient.domain.Product;
import java.util.List;

public interface ShowProducts {
    List<Product> fetchProducts (int menuId);
}
