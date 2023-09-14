package com.perficient.application.service;

import com.perficient.application.error.ProductNotFound;
import com.perficient.application.usecase.ProductDeletion;
import com.perficient.application.usecase.ProductDisplay;
import com.perficient.application.usecase.ProductInsertion;
import com.perficient.application.usecase.ProductModification;
import com.perficient.domain.Product;
import com.perficient.domain.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductManagementService implements ProductDeletion, ProductDisplay, ProductInsertion, ProductModification {

    private static final Logger LOGGER = LogManager.getLogger(ProductManagementService.class);

    private static final String ERR_PRODUCT_NOT_FOUND = "Product not found with value: %s";

    private final ProductRepository productRepository;

    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteById(int productId) {

        productRepository.findById(productId)
                .orElseThrow(
                        () -> new ProductNotFound(String.format(ERR_PRODUCT_NOT_FOUND, productId)));

        LOGGER.info("Attempting to delete product by id: {}", productId);

        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> displayAllBy(String criteria) {
        if (criteria == null) throw new IllegalArgumentException("FilterCriteria object cannot be null!");

        LOGGER.info("Retrieving products...");

        return productRepository.findAllBy(criteria);
    }

    @Override
    public Optional<Product> displayById(int productId) {

        LOGGER.info("Retrieving product by id: {}", productId);

        return productRepository.findById(productId);
    }

    @Override
    public Product addToMenu(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null!");

        productRepository.save(product);

        return product;
    }

    @Override
    public Product modify(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null!");

        productRepository.save(product);

        return product;
    }
}
