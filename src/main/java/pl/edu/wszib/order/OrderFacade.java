package pl.edu.wszib.order;

import lombok.AllArgsConstructor;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderFacade {
    private final Validator validator;
    private final OrderRepository orderRepository;

    public OrderResult create(final OrderDto orderDto) {
        final Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        if (violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid order" + violations.toString());
        }
        final String id = orderDto.getId();
        if (orderRepository.exists(id)) {
            return OrderResult.failure(OrderResultType.ALREADY_EXIST);
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return OrderResult.success(order.toDto());
    }

    public OrderResult update(final OrderDto orderDto) {
        final Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        if (violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid order" + violations.toString());
        }
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
        final Set<ConstraintViolation<PositionDto>> violations = validator.validate(position);
        if (violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid position" + violations.toString());
        }
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

    public OrderResult removePosition(final String orderId,
                                      final Integer positionNumber) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.failure(OrderResultType.NOT_FOUND);
        }
        final OrderDomainResult removePositionResult = order.removePosition(positionNumber);
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
