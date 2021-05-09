package pl.edu.wszib.orderinfrastructure.persistance;

import lombok.AllArgsConstructor;
import pl.edu.wszib.order.Order;
import pl.edu.wszib.order.OrderRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SpringDataJpaOrderRepository implements OrderRepository {
    private final SpringDataJpaOrderDao orderDao;

    @Override
    public boolean exists(String id) {
        return orderDao.existsByPublicId(id);
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderEntity.create(order);
        return orderDao.saveAndFlush(entity)
                .toDomain();
    }

    @Override
    public Optional<Order> get(String id) {
        return orderDao.findByPublicId(id)
                .map(OrderEntity::toDomain);
    }

    @Override
    public Collection<Order> getAll() {
        return orderDao.findAll()
                .stream()
                .map(OrderEntity::toDomain)
                .collect(Collectors.toList());
    }
}
