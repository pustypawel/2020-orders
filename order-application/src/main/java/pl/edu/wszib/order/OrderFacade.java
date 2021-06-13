package pl.edu.wszib.order;

import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import pl.edu.wszib.order.api.OrderDto;
import pl.edu.wszib.order.api.PositionDto;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderFacade {
    private final OrderRepository orderRepository;

    public Either<OrderFailure, OrderDto> create(final OrderDto orderDto) {
        return orderRepository.execute(() -> doCreate(orderDto));
    }

    private Either<OrderFailure, OrderDto> doCreate(OrderDto orderDto) {
        final String id = orderDto.getId();
        if (orderRepository.exists(id)) {
            return Either.left(OrderFailure.alreadyExist(id));
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return Either.right(order.toDto());
    }

    public Either<OrderFailure, OrderDto> update(final OrderDto orderDto) {
        return orderRepository.execute(() -> doUpdate(orderDto));
    }

    private Either<OrderFailure, OrderDto> doUpdate(OrderDto orderDto) {
        final String id = orderDto.getId();
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(order -> order.update(orderDto))
                .map(orderRepository::save)
                .map(Order::toDto);
    }

    public Either<OrderFailure, OrderDto> addPosition(final String id,
                                                      final PositionDto position) {
        return orderRepository.execute(() -> doAddPosition(id, position));
    }

    private Either<OrderFailure, OrderDto> doAddPosition(String id, PositionDto position) {
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(order -> order.addPosition(position))
                .map(orderRepository::save)
                .map(Order::toDto);
    }

    public Either<OrderFailure, OrderDto> removePosition(final String id,
                                                         final Integer positionNumber) {
        return orderRepository.execute(() -> doRemovePosition(id, positionNumber));
    }

    private Either<OrderFailure, OrderDto> doRemovePosition(String id, Integer positionNumber) {
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(order -> order.removePosition(positionNumber))
                .map(orderRepository::save)
                .map(Order::toDto);
    }

    public Either<OrderFailure, OrderDto> submit(final String id) {
        return orderRepository.execute(() -> doSubmit(id));
    }

    private Either<OrderFailure, OrderDto> doSubmit(String id) {
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(Order::submit)
                .map(orderRepository::save)
                .map(Order::toDto);
    }

    public Optional<OrderDto> get(final String id) {
        return orderRepository.get(id)
                .map(Order::toDto);
    }

    public Collection<OrderDto> getAll() {
        return orderRepository.getAll()
                .stream()
                .map(Order::toDto)
                .collect(Collectors.toList());
    }

}
