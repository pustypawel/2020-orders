package pl.edu.wszib.order;

import java.util.Set;

class Order {
    private final String id;
    private final Set<Position> positions;

    Order(final String id,
          final Set<Position> positions) {
        this.id = id;
        this.positions = positions;
    }

    String getId() {
        return id;
    }

    Set<Position> getPositions() {
        return positions;
    }
}
