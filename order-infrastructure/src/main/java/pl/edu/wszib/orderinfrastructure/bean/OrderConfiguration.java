package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderRepository;
import pl.edu.wszib.orderinfrastructure.persistance.SpringDataJpaOrderDao;
import pl.edu.wszib.orderinfrastructure.persistance.SpringDataJpaOrderRepository;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade(SpringDataJpaOrderDao orderDao,
                                   PlatformTransactionManager transactionManager) {
        final OrderRepository orderRepository = orderRepository(orderDao, transactionManager);
        return new OrderFacade(orderRepository);
    }

    @Bean
    public SpringDataJpaOrderRepository orderRepository(SpringDataJpaOrderDao orderDao, PlatformTransactionManager transactionManager) {
        return new SpringDataJpaOrderRepository(orderDao, transactionManager);
    }

}
