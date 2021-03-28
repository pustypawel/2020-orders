package pl.edu.wszib.orderinfrastructure.persistance;

import pl.edu.wszib.order.OrderStatus;

import javax.persistence.*;
import java.util.List;

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

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

//    @OneToOne

    protected OrderEntity() {
        // for JPA
    }

}
