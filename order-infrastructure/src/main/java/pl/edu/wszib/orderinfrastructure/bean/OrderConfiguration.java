package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderRepository;

import javax.validation.Validator;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade() {
        final OrderRepository orderRepository = new OrderRepository();
        return new OrderFacade(orderRepository);
    }

}
