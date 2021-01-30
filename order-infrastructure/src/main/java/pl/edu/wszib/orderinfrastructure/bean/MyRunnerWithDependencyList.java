package pl.edu.wszib.orderinfrastructure.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRunnerWithDependencyList implements CommandLineRunner {
    private final List<MyRunner> runners;

    public MyRunnerWithDependencyList(List<MyRunner> runners) { // może być np List<JakisMojInterfejs> - wtedy wstrzyknięte zostaną wszystkie instancje (albo nawet List<Object> - wtedy wszystie istniejące beany)
        this.runners = runners;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("My runner2, myRunner = " + runners);
    }
}
