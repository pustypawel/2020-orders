package pl.edu.wszib.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class OrderFacadeTest {
    private OrderFacade orderFacade;

    private OrderHelper orderHelper;

    @BeforeEach
    void setUp() {
        final OrderRepository orderRepository = new OrderRepository();
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        final Validator validator = validatorFactory.getValidator();
        final OrderValidator orderValidator = new OrderValidator(validator);
        orderFacade = new OrderFacade(orderValidator, orderRepository);
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
    public void should_not_be_able_to_create_order_with_incorrect_data() {
        // given: We have incorrect order
        final OrderDto incorrectOrder = OrderSamples.sampleIncorrectOrder();

        // when: We try to create order
        final OrderResult result = orderFacade.create(incorrectOrder);

        // then: Order should not be created
        assertTrue(result.isFailure());
        // and: the error type should be OrderResultType.INCORRECT
        assertEquals(OrderResultType.INVALID, result.getType());
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
        assertEquals(OrderResultType.ALREADY_EXIST, result.getType());
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

        // when: We try to remove position to order
        OrderResult result = orderFacade.removePosition(createdOrderId, 0);

        // then: We should have success
        assertTrue(result.isSuccess(), result::toString);
        OrderDto order = orderFacade.get(createdOrderId);
        assertEquals(createdOrder.getPositions().size() - 1, order.getPositions().size(), order::toString);
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

    @Test
    public void should_not_be_able_to_modify_submitted_order() {
        // given: We have submitted order
        OrderDto order = orderHelper.createSubmittedOrder();
        String orderId = order.getId();

        // when: Wy try to modify order
        OrderResult addPositionResult = orderFacade.addPosition(orderId, OrderSamples.samplePosition1());
        OrderResult removePositionResult = orderFacade.removePosition(orderId, 0);
        OrderResult updateResult = orderFacade.update(OrderSamples.sampleOrder2(orderId));
        OrderResult submitResult = orderFacade.submit(orderId);

        // then: All modifying operations should fail
        assertTrue(addPositionResult.isFailure());
        assertTrue(removePositionResult.isFailure());
        assertTrue(updateResult.isFailure());
        assertTrue(submitResult.isFailure());
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