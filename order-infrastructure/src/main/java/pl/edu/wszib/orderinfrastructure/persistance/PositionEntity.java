package pl.edu.wszib.orderinfrastructure.persistance;

import pl.edu.wszib.order.Position;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    PositionEntity(OrderEntity order,
                          Long id,
                          Integer quantity,
                          ProductEntity product) {
        this.order = order;
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.product.setPosition(this);
    }

    static List<PositionEntity> create(List<Position> positions) {
        return positions.stream()
                .map(PositionEntity::create)
                .collect(Collectors.toList());
    }

    static PositionEntity create(Position position) {
        return new PositionEntity(null, null, position.getQuantity(), ProductEntity.create(position.getProduct()));
    }

    void setOrder(OrderEntity order) {
        this.order = order;
    }
}
