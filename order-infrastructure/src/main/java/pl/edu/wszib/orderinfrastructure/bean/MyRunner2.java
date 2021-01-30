package pl.edu.wszib.orderinfrastructure.bean;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MyRunner2 implements CommandLineRunner {
    private final MyRunner myRunner;
//    @Autowired

    @Override
    public void run(String... args) throws Exception {
        System.out.println("My runner2, myRunner = " + myRunner);
    }
}
