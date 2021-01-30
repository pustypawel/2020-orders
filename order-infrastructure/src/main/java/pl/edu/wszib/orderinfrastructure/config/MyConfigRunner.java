package pl.edu.wszib.orderinfrastructure.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyConfigRunner implements CommandLineRunner {
    private final MyConfigByValue myConfigByValue;

    public MyConfigRunner(MyConfigByValue myConfigByValue) {
        this.myConfigByValue = myConfigByValue;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("myProperty " + myConfigByValue.myProperty);
    }
}
