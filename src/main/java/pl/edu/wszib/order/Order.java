package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.HashSet;
import java.util.Set;

class Order {
    private final String id;
    private final Set<Position> positions;
    private final OrderStatus status;

    Order(final String id,
          final Set<Position> positions,
          final OrderStatus status) {
        this.id = id;
        this.positions = positions;
        this.status = status;
    }

    static Order create(OrderDto orderDto) {
        return new Order(orderDto.getId(),
                Position.create(orderDto.getPositions()),
                orderDto.getStatus());
    }

    OrderDto toDto() {
        return new OrderDto(id, Position.toDto(positions), status);
    }

    String getId() {
        return id;
    }

    Order addPosition(final PositionDto position) {
        final Set<Position> newPositions = new HashSet<>(this.positions);
        newPositions.add(Position.create(position));
        return new Order(id, newPositions, status);
    }

    Order removePosition(final PositionDto position) {
        final Set<Position> newPositions = new HashSet<>(this.positions);
        newPositions.remove(Position.create(position));
        return new Order(id, newPositions, status);
    }

    Order submit() {
        return new Order(this.id, this.positions, OrderStatus.SUBMITTED);
    }

}
