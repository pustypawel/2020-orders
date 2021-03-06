package pl.edu.wszib.order;

import io.vavr.control.Either;
import pl.edu.wszib.order.api.OrderDto;
import pl.edu.wszib.order.api.OrderStatus;
import pl.edu.wszib.order.api.PositionDto;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private final List<Position> positions;
    private final OrderStatus status;

    public Order(final String id,
                 final List<Position> positions,
                 final OrderStatus status) {
        this.id = id;
        this.positions = positions;
        this.status = status;
    }

    OrderDto toDto() {
        return new OrderDto(id, Position.toDto(positions), status);
    }

    public String getId() {
        return id;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public OrderStatus getStatus() {
        return status;
    }

    static Order create(OrderDto orderDto) {
        return new Order(orderDto.getId(),
                Position.create(orderDto.getPositions()),
                orderDto.getStatus());
    }

    Either<OrderFailure, Order> update(OrderDto orderDto) {
        if (status == OrderStatus.SUBMITTED) {
            return Either.left(OrderFailure.alreadySubmitted(id));
        }
        return Either.right(create(orderDto));
    }

    Either<OrderFailure, Order> addPosition(final PositionDto position) {
        if (status == OrderStatus.SUBMITTED) {
            return Either.left(OrderFailure.alreadySubmitted(id));
        }
        final List<Position> newPositions = new ArrayList<>(this.positions);
        newPositions.add(Position.create(position));
        return Either.right(new Order(id, newPositions, status));
    }

    Either<OrderFailure, Order> removePosition(final Integer positionNumber) {
        if (status == OrderStatus.SUBMITTED) {
            return Either.left(OrderFailure.alreadySubmitted(id));
        }
        if (positionNumber < this.positions.size()) {
            final Position positionToRemove = this.positions.get(positionNumber);
            if (positionToRemove == null) {
                return Either.left(OrderFailure.positionNotFound(id, positionNumber));
            }
            final List<Position> newPositions = new ArrayList<>(this.positions);
            newPositions.remove(positionToRemove);
            return Either.right(new Order(id, newPositions, status));
        } else {
            return Either.left(OrderFailure.positionNotFound(id, positionNumber));
        }
    }

    Either<OrderFailure, Order> submit() {
        if (status == OrderStatus.SUBMITTED) {
            return Either.left(OrderFailure.alreadySubmitted(id));
        }
        return Either.right(new Order(this.id, this.positions, OrderStatus.SUBMITTED));
    }

}
