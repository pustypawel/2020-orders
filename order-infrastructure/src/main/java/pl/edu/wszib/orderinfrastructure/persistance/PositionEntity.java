package pl.edu.wszib.orderinfrastructure.persistance;

import javax.persistence.*;

@Entity
@Table(name = "POSITIONS")
public class PositionEntity {
    @ManyToOne(optional = false)
    private OrderEntity order;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne(optional = false, cascade = CascadeType.ALL, mappedBy = "position", orphanRemoval = true)
    private ProductEntity product;

    protected PositionEntity() {
        // for JPA
    }
}
