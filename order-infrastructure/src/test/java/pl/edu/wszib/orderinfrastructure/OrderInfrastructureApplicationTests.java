package pl.edu.wszib.orderinfrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pl.edu.wszib.order.api.OrderDto;
import pl.edu.wszib.order.client.OrderClient;
import pl.edu.wszib.order.client.OrderClientConfig;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderInfrastructureApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
		OrderClientConfig config = new OrderClientConfig("http://localhost:" + port);
		final OrderClient client = OrderClient.create(config);
		Collection<OrderDto> orders = client.getAll();
		System.out.println("Orders: " + orders);
	}

}
