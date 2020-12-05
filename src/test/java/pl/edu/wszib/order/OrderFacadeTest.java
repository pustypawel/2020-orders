package pl.edu.wszib.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class OrderFacadeTest {
    private OrderFacade orderFacade;

    @BeforeEach
    void setUp() {
        final OrderRepository orderRepository = new OrderRepository();
        orderFacade = new OrderFacade(orderRepository);
    }

    @Test
    public void should_be_able_to_create_order() {
        // given: We have simple, correct order
        final Order correctOrder = correctOrder();

        // when: We try to create order
        final OrderResult result = orderFacade.create(correctOrder);

        // then: Order should be created
        assertTrue(result.isSuccess());
    }

    @Test
    public void should_not_be_able_to_create_order_with_the_same_id() {
        fail();
    }

    @Test
    public void should_be_able_to_get_existing_order_by_id() {
        fail();
    }

    @Test
    public void should_not_be_able_to_get_not_existing_order_by_id() {
        fail();
    }

    @Test
    public void should_be_able_to_update_order() {
        fail();
    }

    @Test
    public void should_not_be_able_to_update_not_existing_order() {
        fail();
    }

    @Test
    public void should_be_able_to_add_position_to_order() {
        // TODO: test, implementacja, wymyślić inne testy
        fail();
    }

    @Test
    public void should_be_able_to_remove_position_from_order() {
        // TODO: test, implementacja, wymyślić inne testy
        fail();
    }

    @Test
    public void should_be_able_to_submit_order() {
        // TODO: test, implementacja, wymyślić inne testy
        fail();
    }

    private Order correctOrder() {
        return new Order("TEST_ORDER",
                Set.of(new Position(1, 1, new Product("Test product", new BigDecimal("20.05")))));
    }
}