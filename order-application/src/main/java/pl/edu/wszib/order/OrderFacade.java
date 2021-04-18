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

    //TODO IMPL using Either:
    public Either<OrderFailure, OrderDto> submit(final String id) {
        // 1 option
//        Optional<Order> order1 = orderRepository.get(id);
//        if (order1.isPresent()) {
//            final OrderDomainResult submitResult = order1.get().submit();
//            if (submitResult.isFailure()) {
//                return submitResult.toFailureApi();
//            }
//            orderRepository.save(submitResult.getOrder().get());
//            return submitResult.toSuccessApi();
//        } else {
//            return OrderResult.notFound(id);
//        }
        // 2 option
//        Optional<Order> order1 = orderRepository.get(id);
//        if (order1.isPresent()) {
//            Either<OrderFailure, Order> order2 = order1.get().submit();
//            if (order2.isRight()) {
//                return Either.right(orderRepository.save(order2.get())
//                        .toDto());
//            } else {
//                return Either.left(order2.getLeft());
//            }
//        } else {
//            return Either.left(OrderFailure.notFound(id));
//        }
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
