package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderRepository;
import pl.edu.wszib.orderinfrastructure.persistance.SpringDataJpaOrderDao;
import pl.edu.wszib.orderinfrastructure.persistance.SpringDataJpaOrderRepository;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade(SpringDataJpaOrderDao orderDao) {
        final OrderRepository orderRepository = new SpringDataJpaOrderRepository(orderDao);
        return new OrderFacade(orderRepository);
    }

}
