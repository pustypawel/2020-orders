package pl.edu.wszib.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import static org.junit.jupiter.api.Assertions.*;

class OrderFacadeTest {
    private OrderFacade orderFacade;

    private OrderHelper orderHelper;

    @BeforeEach
    void setUp() {
        final OrderRepository orderRepository = new OrderRepository();
        orderFacade = new OrderFacade(orderRepository);
        orderHelper = new OrderHelper(orderFacade);
    }

    @Test
    public void should_be_able_to_create_order() {
        // given: We have simple, correct order
        final OrderDto correctOrder = OrderSamples.sampleOrder();

        // when: We try to create order
        final OrderResult result = orderFacade.create(correctOrder);

        // then: Order should be created
        assertTrue(result.isSuccess());
    }

    @Test
    public void should_not_be_able_to_create_order_with_the_same_id() {
        // given: We have simple, correct order
        final String orderId = orderHelper.createCorrectOrder().getId();
        // and: We have another order which has the same id
        final OrderDto orderWithTheSameId = OrderSamples.sampleOrder2(orderId);

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
        String orderId = orderHelper.createCorrectOrder().getId();

        // when: We try to get order by id
        OrderDto order = orderFacade.get(orderId);

        // then: We should get order
        assertNotNull(order);
        // and: Id should be the same
        assertEquals(orderId, order.getId());
    }

    @Test
    public void should_not_be_able_to_get_not_existing_order_by_id() {
        // given: We have not existing order id
        String notExistingOrderId = OrderSamples.notExistingOrderId();

        // when: We try to get order by id
        OrderDto order = orderFacade.get(notExistingOrderId);

        // then: We should get no order
        assertNull(order);
    }

    @Test
    public void should_be_able_to_update_order() {
        // given: We have created correct order
        String orderId = orderHelper.createCorrectOrder().getId();
        // and: We have modified order
        OrderDto modifiedOrder = OrderSamples.sampleOrder2(orderId);

        // when: We try to update order
        OrderResult result = orderFacade.update(modifiedOrder);

        // then: We should have success
        assertTrue(result.isSuccess(), result::toString);
    }

    @Test
    public void should_be_able_to_add_position_to_order() {
        // given: We have created correct order
        String orderId = orderHelper.createCorrectOrder().getId();
        // and: We have position to add
        PositionDto position = OrderSamples.samplePosition1();

        // when: We try to add position to order
        OrderResult result = orderFacade.addPosition(orderId, position);

        // then: We should have success
        assertTrue(result.isSuccess(), result::toString);
        OrderDto order = orderFacade.get(orderId);
        assertTrue(containsPosition(order, position), order::toString);
    }

    @Test
    public void should_be_able_to_remove_position_from_order() {
        // given: We have created correct order
        OrderDto createdOrder = orderHelper.createCorrectOrder();
        String createdOrderId = createdOrder.getId();
        // and: We have position to remove
        PositionDto position = createdOrder.getPositions().iterator().next();

        // when: We try to remove position to order
        OrderResult result = orderFacade.removePosition(createdOrderId, position);

        // then: We should have success
        assertTrue(result.isSuccess(), result::toString);
        OrderDto order = orderFacade.get(createdOrderId);
        assertTrue(notContainsPosition(order, position), order::toString);
    }

    @Test
    public void should_be_able_to_submit_order() {
        // given: We have created correct order
        String orderId = orderHelper.createCorrectOrder().getId();

        // when: We try to submit
        OrderResult result = orderFacade.submit(orderId);

        // then: We should have success
        assertTrue(result.isSuccess(), result::toString);
    }

    private boolean notContainsPosition(OrderDto order,
                                        PositionDto position) {
        return !containsPosition(order, position);
    }

    private boolean containsPosition(OrderDto order,
                                     PositionDto position) {
        return order.getPositions().contains(position);
    }

}