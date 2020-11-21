package pl.edu.wszib.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public boolean exists(final String id) {
        return orders.containsKey(id);
    }

    public void save(final Order order) {
        orders.put(order.getId(), order);
    }

    public Order get(final String id) {
        return orders.get(id);
    }

    public Collection<Order> getAll() {
        return orders.values();
    }
}
