package pl.edu.wszib.order;

import java.util.Collection;

public class OrderFacade {
    private final OrderRepository orderRepository;

    public OrderFacade(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResult create(final Order order) {
        final String id = order.getId();
        if (orderRepository.exists(id)) {
            return OrderResult.ALREADY_EXIST;
        }
        orderRepository.save(order);
        return OrderResult.OK;
    }

    public OrderResult update(final Order order) {
        if (!orderRepository.exists(order.getId())) {
            return OrderResult.NOT_FOUND;
        }
        orderRepository.save(order);
        return OrderResult.OK;
    }

    public OrderResult addPosition(final String orderId,
                                   final Position position) {
        final Order order = get(orderId);
        if (order == null) {
            return OrderResult.NOT_FOUND;
        }
        final Order orderWithPosition = order.addPosition(position);
        orderRepository.save(orderWithPosition);
        return OrderResult.OK;
    }

    public OrderResult removePosition(final String orderId,
                                      final Position position) {
        final Order order = get(orderId);
        if (order == null) {
            return OrderResult.NOT_FOUND;
        }
        final Order orderWithPosition = order.removePosition(position);
        orderRepository.save(orderWithPosition);
        return OrderResult.OK;
    }

    public OrderResult submit(final String orderId) {
        final Order order = get(orderId);
        if (order == null) {
            return OrderResult.NOT_FOUND;
        }
        final Order orderSubmitted = order.submit();
        orderRepository.save(orderSubmitted);
        return OrderResult.OK;
    }
//    private OrderResult doWithOrder(final String orderId,
//                                    final Function<Order, Order> changingFunction) {
//        final Order order = get(orderId);
//        if (order == null) {
//            return OrderResult.NOT_FOUND;
//        }
//        Order changedOrder = changingFunction.apply(order);
//        orderRepository.save(changedOrder);
//        return OrderResult.OK;

//    }

    /**
     * @param id - should not be null
     * @return null if not found
     */
    public Order get(final String id) {
        return orderRepository.get(id);
    }

    public Collection<Order> getAll() {
        return orderRepository.getAll();
    }
}
