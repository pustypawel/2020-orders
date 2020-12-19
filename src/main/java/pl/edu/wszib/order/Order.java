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

    OrderDto toDto() {
        return new OrderDto(id, Position.toDto(positions), status);
    }

    String getId() {
        return id;
    }

    static Order create(OrderDto orderDto) {
        return new Order(orderDto.getId(),
                Position.create(orderDto.getPositions()),
                orderDto.getStatus());
    }

    OrderDomainResult update(OrderDto orderDto) {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.failure(OrderResultType.ALREADY_SUBMITTED);
        }
        return OrderDomainResult.success(create(orderDto));
    }

    OrderDomainResult addPosition(final PositionDto position) {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.failure(OrderResultType.ALREADY_SUBMITTED);
        }
        final Set<Position> newPositions = new HashSet<>(this.positions);
        newPositions.add(Position.create(position));
        return OrderDomainResult.success(new Order(id, newPositions, status));
    }

    OrderDomainResult removePosition(final Integer positionNumber) {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.failure(OrderResultType.ALREADY_SUBMITTED);
        }
        final Position positionToRemove = this.positions.stream()
                .filter(position -> position.hasNumber(position))
                .findFirst()
                .orElse(null);  // TODO obsłużyć
        if (positionToRemove == null) {
            return OrderDomainResult.failure(OrderResultType.POSITION_NOT_FOUND);
        }
        final Set<Position> newPositions = new HashSet<>(this.positions);
        newPositions.remove(positionToRemove);
        return OrderDomainResult.success(new Order(id, newPositions, status));
    }

    OrderDomainResult submit() {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.failure(OrderResultType.ALREADY_SUBMITTED);
        }
        return OrderDomainResult.success(new Order(this.id, this.positions, OrderStatus.SUBMITTED));
    }
}
