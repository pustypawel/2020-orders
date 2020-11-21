package pl.edu.wszib.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    boolean exists(final String id) {
        return orders.containsKey(id);
    }

    void save(final Order order) {
        orders.put(order.getId(), order);
    }

    Order get(final String id) {
        return orders.get(id);
    }

    Collection<Order> getAll() {
        return orders.values();
    }
}
