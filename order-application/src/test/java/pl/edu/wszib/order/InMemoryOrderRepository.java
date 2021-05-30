package pl.edu.wszib.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    @Override
    public boolean exists(final String id) {
        return orders.containsKey(id);
    }

    @Override
    public Order save(final Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<Order> get(final String id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public Collection<Order> getAll() {
        return orders.values();
    }

    @Override
    public <TOut> TOut execute(Supplier<TOut> executable) {
        return executable.get();
    }
}
