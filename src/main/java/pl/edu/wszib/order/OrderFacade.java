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
            return OrderResult.failure(OrderResult.Type.ALREADY_EXIST);
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return OrderResult.success(order.toDto());
    }

    public OrderResult update(final OrderDto orderDto) {
        if (!orderRepository.exists(orderDto.getId())) {
            return OrderResult.failure(OrderResult.Type.NOT_FOUND);
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return OrderResult.success(order.toDto());
    }

    public OrderResult addPosition(final String orderId,
                                   final PositionDto position) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResult.Type.NOT_FOUND);
        }
        final Order orderWithPosition = order.addPosition(position);
        orderRepository.save(orderWithPosition);
        return OrderResult.success(order.toDto());
    }

    // TODO usuwanie pozycji na podstawie jej numeru
    public OrderResult removePosition(final String orderId,
                                      final PositionDto position) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResult.Type.NOT_FOUND);
        }
        final Order orderWithPosition = order.removePosition(position);
        orderRepository.save(orderWithPosition);
        return OrderResult.success(order.toDto());
    }

    public OrderResult submit(final String orderId) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResult.Type.NOT_FOUND);
        }
        final Order orderSubmitted = order.submit();
        orderRepository.save(orderSubmitted);
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
