package com.perficient.orderapp.domain.mother;

import com.perficient.orderapp.domain.ProductItem;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductItemMother {
    public static UUID product_id_1 = UUID.randomUUID();
    public static UUID product_id_2 = UUID.randomUUID();
    public static BigDecimal TOTAL_PRICE = BigDecimal.valueOf(14.3);

    public static BigDecimal total_price = BigDecimal.valueOf(14.3);
    public static ProductItemBuilder product1 = new ProductItemBuilder()
            .id(product_id_1)
            .name("Salmon")
            .category("Fish")
            .price(BigDecimal.TEN)
            .discount(BigDecimal.ZERO);
    public static ProductItemBuilder product2 = new ProductItemBuilder()
            .id(product_id_2)
            .name("Rice")
            .category("Rice")
            .price(BigDecimal.valueOf(4.3))
            .discount(BigDecimal.ZERO);

    public static class ProductItemBuilder {
        private UUID id;
        private String name;
        private String category;
        private BigDecimal price;
        private BigDecimal discount;

        public ProductItemBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public ProductItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductItemBuilder category(String category) {
            this.category = category;
            return this;
        }

        public ProductItemBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductItemBuilder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public ProductItem build() {
            return new ProductItem(id, name, category, price, discount);
        }

    }
}
