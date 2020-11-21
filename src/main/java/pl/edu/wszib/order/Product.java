package pl.edu.wszib.order;

import java.math.BigDecimal;

class Product {
    private final String name;
    private final BigDecimal price;

    Product(final String name,
            final BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
