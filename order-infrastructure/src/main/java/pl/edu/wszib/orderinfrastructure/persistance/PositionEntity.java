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

    protected PositionEntity() {
        // for JPA
    }
}
