package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyConfiguration {

    @Bean
//    @Primary
    public MyRunner myRunner1() {
        return new MyRunner();
    }

    @Bean
    public MyRunner myRunner2() {
        return new MyRunner();
    }

}
