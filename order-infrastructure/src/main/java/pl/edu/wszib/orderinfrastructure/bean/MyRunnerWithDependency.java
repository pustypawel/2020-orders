package pl.edu.wszib.orderinfrastructure.bean;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunnerWithDependency implements CommandLineRunner {
    private final MyRunner myRunner;

    public MyRunnerWithDependency(@Qualifier("myRunner1") MyRunner myRunner) {
        this.myRunner = myRunner;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("My runner2, myRunner = " + myRunner);
    }
}
