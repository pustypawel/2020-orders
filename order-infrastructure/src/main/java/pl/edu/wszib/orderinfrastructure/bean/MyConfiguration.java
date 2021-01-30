package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyRunner myRunner() {
        return new MyRunner();
    }

}
