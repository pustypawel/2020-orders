package pl.edu.wszib.order;

import lombok.AllArgsConstructor;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderFacade {
    private final OrderRepository orderRepository;

    public OrderResult create(final OrderDto orderDto) {
        final String id = orderDto.getId();
        if (orderRepository.exists(id)) {
            return OrderResult.alreadyExist(id);
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return OrderResult.success(order.toDto());
    }

    public OrderResult update(final OrderDto orderDto) {
        final String id = orderDto.getId();
        return orderRepository.get(id)
                .map(order -> {
                    final OrderDomainResult updateResult = order.update(orderDto);
                    if (updateResult.isFailure()) {
                        return updateResult.toFailureApi();
                    }
                    orderRepository.save(updateResult.getOrder().get());
                    return updateResult.toSuccessApi();
                })
                .orElseGet(() -> OrderResult.notFound(id));
    }

    public OrderResult addPosition(final String orderId,
                                   final PositionDto position) {
        return orderRepository.get(orderId)
                .map(order -> {
                    final OrderDomainResult addPositionResult = order.addPosition(position);
                    if (addPositionResult.isFailure()) {
                        return addPositionResult.toFailureApi();
                    }
                    orderRepository.save(addPositionResult.getOrder().get());
                    return addPositionResult.toSuccessApi();
                })
                .orElseGet(() -> OrderResult.notFound(orderId));
    }

    public OrderResult removePosition(final String orderId,
                                      final Integer positionNumber) {
        return orderRepository.get(orderId)
                .map(order -> {
                    final OrderDomainResult removePositionResult = order.removePosition(positionNumber);
                    if (removePositionResult.isFailure()) {
                        return removePositionResult.toFailureApi();
                    }
                    orderRepository.save(removePositionResult.getOrder().get());
                    return removePositionResult.toSuccessApi();
                })
                .orElseGet(() -> OrderResult.notFound(orderId));
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
