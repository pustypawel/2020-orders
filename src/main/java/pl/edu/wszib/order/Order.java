package pl.edu.wszib.order;

import java.util.HashSet;
import java.util.Set;

class Order {
    private final String id;
    private final Set<Position> positions;
    private final OrderStatus status;

    Order(final String id,
          final Set<Position> positions,
          OrderStatus status) {
        this.id = id;
        this.positions = positions;
        this.status = status;
    }

    String getId() {
        return id;
    }

    Set<Position> getPositions() {
        return positions;
    }

    Order addPosition(final Position position) {
        final Set<Position> newPositions = new HashSet<>(this.positions);
        newPositions.add(position);
        return new Order(id, newPositions, status);
    }

    public boolean containsPosition(final Position position) {
        return positions.contains(position);
    }
}
