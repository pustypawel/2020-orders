package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;
import pl.edu.wszib.order.dto.ProductDto;

import java.math.BigDecimal;
import java.util.Set;

public class OrderSamples {
    public static String notExistingOrderId() {
        return "NOT_EXISTING_ORDER_ID";
    }

    public static OrderDto sampleOrder() {
        return new OrderDto("TEST_ORDER", samplePositions(), OrderStatus.DRAFT);
    }

    public static OrderDto sampleOrder2(String orderId) {
        return new OrderDto(orderId,
                Set.of(samplePosition1(), samplePosition2()), OrderStatus.DRAFT);
    }

    static Set<PositionDto> samplePositions() {
        return Set.of(samplePosition1());
    }

    static PositionDto samplePosition1() {
        return new PositionDto(1, 2, new ProductDto("Test product", new BigDecimal("20.05")));
    }

    static PositionDto samplePosition2() {
        return new PositionDto(2, 5, new ProductDto("Test product 2", new BigDecimal("10.05")));
    }

    public static OrderDto sampleIncorrectOrder() {
        return new OrderDto(null, null, null);
    }
}
