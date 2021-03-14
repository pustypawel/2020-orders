package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderRepository;
import pl.edu.wszib.order.OrderValidator;

import javax.validation.Validator;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade(final Validator validator) {
        final OrderValidator orderValidator = new OrderValidator(validator);
        final OrderRepository orderRepository = new OrderRepository();
        return new OrderFacade(orderValidator, orderRepository);
    }

}
