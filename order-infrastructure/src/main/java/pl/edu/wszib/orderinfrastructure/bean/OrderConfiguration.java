package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderRepository;
import pl.edu.wszib.order.OrderStatus;
import pl.edu.wszib.order.OrderValidator;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;
import pl.edu.wszib.order.dto.ProductDto;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Configuration
public class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade(final Validator validator) {
        final OrderValidator orderValidator = new OrderValidator(validator);
        final OrderRepository orderRepository = new OrderRepository();
        final OrderFacade orderFacade = new OrderFacade(orderValidator, orderRepository);
        orderFacade.create(new OrderDto(UUID.randomUUID().toString(),
                List.of(new PositionDto(1, new ProductDto("Tes prod", BigDecimal.valueOf(100)))),
                OrderStatus.DRAFT));
        return orderFacade;
    }

}
