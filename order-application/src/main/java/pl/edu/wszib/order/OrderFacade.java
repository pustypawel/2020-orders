package pl.edu.wszib.order;

import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import javax.swing.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderFacade {
    private final OrderRepository orderRepository;

    public Either<OrderFailure, OrderDto> create(final OrderDto orderDto) {
        final String id = orderDto.getId();
        if (orderRepository.exists(id)) {
            return Either.left(OrderFailure.alreadyExist(id));
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return Either.right(order.toDto());
    }

    public Either<OrderFailure, OrderDto> update(final OrderDto orderDto) {
        final String id = orderDto.getId();
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(order -> order.update(orderDto))

                .map(Order::toDto);
    }

    public Either<OrderFailure, OrderDto> addPosition(final String id,
                                                      final PositionDto position) {
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(order -> order.addPosition(position))
                .map(orderRepository::save)
                .map(Order::toDto);
    }

    public Either<OrderFailure, OrderDto> removePosition(final String id,
                                                         final Integer positionNumber) {
        return Option.ofOptional(orderRepository.get(id))
                .toEither(() -> OrderFailure.notFound(id))
                .flatMap(order -> order.removePosition(positionNumber))
                .map(orderRepository::save)
                .map(Order::toDto);
    }

    public OrderResult submit(final String orderId) {
        return orderRepository.get(orderId)
                .map(order -> {
                    final OrderDomainResult submitResult = order.submit();
                    if (submitResult.isFailure()) {
                        return submitResult.toFailureApi();
                    }
                    orderRepository.save(submitResult.getOrder().get());
                    return submitResult.toSuccessApi();
                })
                .orElseGet(() -> OrderResult.notFound(orderId));
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
