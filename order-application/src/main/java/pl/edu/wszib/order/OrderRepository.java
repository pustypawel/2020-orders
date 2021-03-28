package pl.edu.wszib.order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {
    boolean exists(String id);

    void save(Order order);

    Optional<Order> get(String id);

    Collection<Order> getAll();
}
