package pl.edu.wszib.order;

import lombok.Value;
import pl.edu.wszib.order.dto.OrderDto;

import java.util.Optional;

@Value
public class OrderResult {
    OrderFailureType type;
    OrderDto order;
    String errorMessage;

    public static OrderResult failure(final OrderFailureType type,
                                      final String errorMessage) {
        if (type == OrderFailureType.OK) {
            throw new IllegalArgumentException("type can't be " + OrderFailureType.OK);
        }
        return new OrderResult(type, null, errorMessage);
    }

    public static OrderResult success(final OrderDto order) {
        return new OrderResult(OrderFailureType.OK, order, null);
    }

    public static OrderResult notFound(final String orderId) {
        return failure(OrderFailureType.NOT_FOUND, "Order with id " + orderId + " not found");
    }

    public static OrderResult invalid(final String errorMessage) {
        return failure(OrderFailureType.INVALID, errorMessage);
    }

    public boolean isSuccess() {
        return type == OrderFailureType.OK;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public Optional<OrderDto> getOrder() {
        return Optional.ofNullable(order);
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }
}
