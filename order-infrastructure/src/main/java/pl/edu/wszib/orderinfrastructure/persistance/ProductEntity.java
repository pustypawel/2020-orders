package pl.edu.wszib.orderinfrastructure.persistance;

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
}
