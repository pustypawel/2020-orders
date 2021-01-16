package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final String id;
    private final List<Position> positions;
    private final OrderStatus status;

    Order(final String id,
          final List<Position> positions,
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
            return OrderDomainResult.alreadySubmitted(id);
        }
        return OrderDomainResult.success(create(orderDto));
    }

    OrderDomainResult addPosition(final PositionDto position) {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.alreadySubmitted(id);
        }
        final List<Position> newPositions = new ArrayList<>(this.positions);
        newPositions.add(Position.create(position));
        return OrderDomainResult.success(new Order(id, newPositions, status));
    }

    OrderDomainResult removePosition(final Integer positionNumber) {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.alreadySubmitted(id);
        }
        if (positionNumber < this.positions.size()) {
            final Position positionToRemove = this.positions.get(positionNumber);
            if (positionToRemove == null) {
                return OrderDomainResult.positionNotFound(id, positionNumber);
            }
            final List<Position> newPositions = new ArrayList<>(this.positions);
            newPositions.remove(positionToRemove);
            return OrderDomainResult.success(new Order(id, newPositions, status));
        } else {
            return OrderDomainResult.positionNotFound(id, positionNumber);
        }
    }

    OrderDomainResult submit() {
        if (status == OrderStatus.SUBMITTED) {
            return OrderDomainResult.alreadySubmitted(id);
        }
        return OrderDomainResult.success(new Order(this.id, this.positions, OrderStatus.SUBMITTED));
    }
}
