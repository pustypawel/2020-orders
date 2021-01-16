package pl.edu.wszib.order;

import lombok.AllArgsConstructor;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderFacade {
    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;

    public OrderResult create(final OrderDto orderDto) {
        final OrderResult validationResult = orderValidator.validate(orderDto);
        if (validationResult != null) {
            return validationResult;
        }
        final String id = orderDto.getId();
        if (orderRepository.exists(id)) {
            return OrderResult.alreadyExist(id);
        }
        Order order = Order.create(orderDto);
        orderRepository.save(order);
        return OrderResult.success(order.toDto());
    }

    public OrderResult update(final OrderDto orderDto) {
        final OrderResult validationResult = orderValidator.validate(orderDto);
        if (validationResult != null) {
            return validationResult;
        }
        final String id = orderDto.getId();
        final Order order = orderRepository.get(id);
        if (order == null) {
            return OrderResult.notFound(id);
        }
        final OrderDomainResult updateResult = order.update(orderDto);
        if (updateResult.isFailure()) {
            return updateResult.toFailureApi();
        }
        orderRepository.save(updateResult.getOrder());
        return updateResult.toSuccessApi();
    }

    public OrderResult addPosition(final String orderId,
                                   final PositionDto position) {
        final OrderResult validationResult = orderValidator.validate(position);
        if (validationResult != null) {
            return validationResult;
        }
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.notFound(orderId);
        }
        final OrderDomainResult addPositionResult = order.addPosition(position);
        if (addPositionResult.isFailure()) {
            return addPositionResult.toFailureApi();
        }
        orderRepository.save(addPositionResult.getOrder());
        return addPositionResult.toSuccessApi();
    }

    public OrderResult removePosition(final String orderId,
                                      final Integer positionNumber) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.notFound(orderId);
        }
        final OrderDomainResult removePositionResult = order.removePosition(positionNumber);
        if (removePositionResult.isFailure()) {
            return removePositionResult.toFailureApi();
        }
        orderRepository.save(removePositionResult.getOrder());
        return removePositionResult.toSuccessApi();
    }

    public OrderResult submit(final String orderId) {
        final Order order = orderRepository.get(orderId);
        if (order == null) {
            return OrderResult.notFound(orderId);
        }
        final OrderDomainResult submitResult = order.submit();
        if (submitResult.isFailure()) {
            return submitResult.toFailureApi();
        }
        orderRepository.save(submitResult.getOrder());
        return submitResult.toSuccessApi();
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
