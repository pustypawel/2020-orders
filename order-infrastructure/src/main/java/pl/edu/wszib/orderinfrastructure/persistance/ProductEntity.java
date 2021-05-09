package pl.edu.wszib.orderinfrastructure.persistance;

import pl.edu.wszib.order.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @OneToOne(optional = false)
    private PositionEntity position;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    protected ProductEntity() {
        // for JPA
    }

    ProductEntity(PositionEntity position, Long id, String name, BigDecimal price) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    static ProductEntity create(Product product) {
        return new ProductEntity(null, null, product.getName(), product.getPrice());
    }

    void setPosition(PositionEntity position) {
        this.position = position;
    }
}
