package pl.edu.wszib.order;

import java.math.BigDecimal;
import java.util.Set;

public class OrderSamples {
    public static String notExistingOrderId() {
        return "NOT_EXISTING_ORDER_ID";
    }

    public static Order sampleOrder() {
        return new Order("TEST_ORDER", samplePositions(), OrderStatus.DRAFT);
    }

    public static Order sampleOrder2(String orderId) {
        return new Order(orderId,
                Set.of(samplePosition1(), samplePosition2()), OrderStatus.DRAFT);
    }

    static Set<Position> samplePositions() {
        return Set.of(samplePosition1());
    }

    static Position samplePosition1() {
        return new Position(1, 2, new Product("Test product", new BigDecimal("20.05")));
    }

    static Position samplePosition2() {
        return new Position(2, 5, new Product("Test product 2", new BigDecimal("10.05")));
    }
}
