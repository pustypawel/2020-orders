package pl.edu.wszib.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        // given: We have simple, correct order
        final Order correctOrder = createCorrectOrder();
        // and: We have another order which has the same id
        final Order orderWithTheSameId = orderWithTheSameId(correctOrder);

        // when: We try to create order with the same id
        OrderResult result = orderFacade.create(orderWithTheSameId);

        // then: Order should not be created
        assertTrue(result.isFailure());
        // and: the failure reason should be ALREADY_EXIST
        assertEquals(OrderResult.ALREADY_EXIST, result);
    }

    @Test
    public void should_be_able_to_get_existing_order_by_id() {
        // given: We have created correct order
        String orderId = createCorrectOrder().getId();

        // when: We try to get order by id
        Order order = orderFacade.get(orderId);

        // then: We should get order
        assertNotNull(order);
    }

    @Test
    public void should_not_be_able_to_get_not_existing_order_by_id() {
        // given: We have not existing order id
        String notExistingOrderId = notExistingOrderId();

        // when: We try to get order by id
        Order order = orderFacade.get(notExistingOrderId);

        // then: We should get no order
        assertNull(order);
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

    private Order createCorrectOrder() {
        final Order correctOrder = correctOrder();
        orderFacade.create(correctOrder);
        return correctOrder;
    }

    private String notExistingOrderId() {
        return "NOT_EXISTING_ORDER_ID";
    }

    private Order correctOrder() {
        return new Order("TEST_ORDER",
                Set.of(new Position(1, 1, new Product("Test product", new BigDecimal("20.05")))));
    }

    private Order orderWithTheSameId(Order order) {
        String id = order.getId();
        return new Order(id,
                Set.of(new Position(1, 2, new Product("Test product", new BigDecimal("20.05")))));
    }
}