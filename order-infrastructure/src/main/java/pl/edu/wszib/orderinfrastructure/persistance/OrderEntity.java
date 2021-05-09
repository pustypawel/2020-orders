package pl.edu.wszib.orderinfrastructure.persistance;

import pl.edu.wszib.order.Order;
import pl.edu.wszib.order.OrderStatus;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String publicId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order", orphanRemoval = true)
    private List<PositionEntity> positions;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

//    @OneToOne

    protected OrderEntity() {
        // for JPA
    }

    OrderEntity(Long id,
                String publicId,
                List<PositionEntity> positions,
                OrderStatus status) {
        this.id = id;
        this.publicId = publicId;
        this.positions = positions;
        this.positions.forEach(position -> position.setOrder(this));
        this.status = status;
    }

    static OrderEntity create(Order order) {
        List<PositionEntity> positions = PositionEntity.create(order.getPositions());
        return new OrderEntity(null,
                order.getId(),
                positions,
                order.getStatus());
    }

    Order toDomain() {
        //FIXME impl!
        return null;
    }
}
