package pl.edu.wszib.order;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public interface OrderRepository {
    boolean exists(String id);

    Order save(Order order);

    Optional<Order> get(String id);

    Collection<Order> getAll();

    <TOut> TOut execute(Supplier<TOut> executable);
}
