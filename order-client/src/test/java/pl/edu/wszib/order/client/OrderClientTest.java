package pl.edu.wszib.order.client;

import org.junit.jupiter.api.Test;
import pl.edu.wszib.order.api.OrderDto;

import java.util.Collection;

public class OrderClientTest {

    @Test
    public void test() {
        OrderClientConfig config = new OrderClientConfig("http://localhost:8080");
        final OrderClient client = OrderClient.create(config);
        Collection<OrderDto> orders = client.getAll();
        System.out.println("Orders: " + orders);
    }
}