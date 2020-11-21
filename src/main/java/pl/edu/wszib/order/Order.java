package pl.edu.wszib.order;

import java.util.Set;

public class Order {
    private final String id;
    private Set<Position> positions;

    public Order(final String id,
                 final Set<Position> positions) {
        this.id = id;
        this.positions = positions;
    }

    public String getId() {
        return id;
    }

    public Set<Position> getPositions() {
        return positions;
    }
}
