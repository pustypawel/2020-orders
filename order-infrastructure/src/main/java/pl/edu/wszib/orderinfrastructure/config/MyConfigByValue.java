package pl.edu.wszib.orderinfrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyConfigByValue {
//    @Value("${myProperty}")
    String myProperty;

    public MyConfigByValue(@Value("${myProperty}") String myProperty) {
        this.myProperty = myProperty;
    }
}
