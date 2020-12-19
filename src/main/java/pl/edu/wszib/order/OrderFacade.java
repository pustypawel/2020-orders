package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class OrderFacade {
    private final OrderRepository orderRepository;

    public OrderFacade(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResult create(final OrderDto orderDto) {
        final String id = orderDto.getId();
        if (orderRepository.exists(id)) {
            return OrderResult.failure(OrderResultType.ALREADY_EXIST);
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return OrderResult.success(order.toDto());
    }

    public OrderResult update(final OrderDto orderDto) {
        Order order = orderRepository.get(orderDto.getId());
        if (order == null) {
            return OrderResult.failure(OrderResultType.NOT_FOUND);
        }
        OrderDomainResult updateResult = order.update(orderDto);
        if (updateResult.isFailure()) {
            return OrderResult.failure(updateResult.getType());
        }
        orderRepository.save(updateResult.getOrder());
        return OrderResult.success(order.toDto());
    }

    public OrderResult addPosition(final String orderId,
                                   final PositionDto position) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResultType.NOT_FOUND);
        }
        final OrderDomainResult addPositionResult = order.addPosition(position);
        if (addPositionResult.isFailure()) {
            return OrderResult.failure(addPositionResult.getType());
        }
        orderRepository.save(addPositionResult.getOrder());
        return OrderResult.success(order.toDto());
    }

    // TODO usuwanie pozycji na podstawie jej numeru
    public OrderResult removePosition(final String orderId,
                                      final PositionDto position) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResultType.NOT_FOUND);
        }
        final OrderDomainResult removePositionResult = order.removePosition(position);
        if (removePositionResult.isFailure()) {
            return OrderResult.failure(removePositionResult.getType());
        }
        orderRepository.save(removePositionResult.getOrder());
        return OrderResult.success(order.toDto());
    }

    public OrderResult submit(final String orderId) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResultType.NOT_FOUND);
        }
        final OrderDomainResult submitResult = order.submit();
        if (submitResult.isFailure()) {
            return OrderResult.failure(submitResult.getType());
        }
        orderRepository.save(submitResult.getOrder());
        return OrderResult.success(order.toDto());
    }

    /**
     * @param id - should not be null
     * @return null if not found
     */
    public OrderDto get(final String id) {
        Order order = orderRepository.get(id);
        return order == null ? null : order.toDto();
    }

    public Collection<OrderDto> getAll() {
        return orderRepository.getAll()
                .stream()
                .map(Order::toDto)
                .collect(Collectors.toList());
    }

}
