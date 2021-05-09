package pl.edu.wszib.order;

import lombok.EqualsAndHashCode;
import pl.edu.wszib.order.dto.ProductDto;

import java.math.BigDecimal;

@EqualsAndHashCode
public class Product {
    private final String name;
    private final BigDecimal price;

    Product(final String name,
            final BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    static Product create(ProductDto product) {
        return new Product(product.getName(), product.getPrice());
    }

    ProductDto toDto() {
        return new ProductDto(name, price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
