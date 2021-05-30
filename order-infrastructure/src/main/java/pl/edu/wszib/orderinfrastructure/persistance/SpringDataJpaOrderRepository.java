package pl.edu.wszib.orderinfrastructure.persistance;

import lombok.AllArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;
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

    //TODO remove
    public <TOut> TOut execute2(Supplier<TOut> executable) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                return null;
            }
        });
        return null;
    }

    //TODO remove
    public <TOut> TOut execute3(Supplier<TOut> executable) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName(getClass() + "execute3");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            TOut tOut = executable.get();
            transactionManager.commit(status);
            return tOut;
        } catch (RuntimeException e) {
            transactionManager.rollback(status);
            throw e;
        }
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
