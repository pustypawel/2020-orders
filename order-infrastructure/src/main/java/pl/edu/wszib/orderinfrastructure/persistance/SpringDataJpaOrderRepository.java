package pl.edu.wszib.orderinfrastructure.persistance;

import lombok.AllArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import pl.edu.wszib.order.Order;
import pl.edu.wszib.order.OrderRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SpringDataJpaOrderRepository implements OrderRepository {
    private final SpringDataJpaOrderDao orderDao;
    private final PlatformTransactionManager transactionManager;

    @Override
    @Transactional
    public <TOut> TOut execute(Supplier<TOut> executable) {
        TOut tOut = executable.get();
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        return tOut;
    }

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
