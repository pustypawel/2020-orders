package pl.edu.wszib.orderinfrastructure.persistance;

import pl.edu.wszib.order.Order;
import pl.edu.wszib.order.OrderRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SpringDataJpaOrderRepository implements OrderRepository {
    
    @Override
    public boolean exists(String id) {
        return false;
    }

    @Override
    public Order save(Order order) {
        return order;
    }

    @Override
    public Optional<Order> get(String id) {
        return Optional.empty();
    }

    @Override
    public Collection<Order> getAll() {
        return List.of();
    }
}
