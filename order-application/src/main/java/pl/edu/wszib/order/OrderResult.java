package pl.edu.wszib.order;

import lombok.Value;
import pl.edu.wszib.order.dto.OrderDto;

import java.util.Optional;

@Value
public class OrderResult {
    OrderResultType type;
    OrderDto order;
    String errorMessage;

    public static OrderResult failure(final OrderResultType type,
                                      final String errorMessage) {
        if (type == OrderResultType.OK) {
            throw new IllegalArgumentException("type can't be " + OrderResultType.OK);
        }
        return new OrderResult(type, null, errorMessage);
    }

    public static OrderResult success(final OrderDto order) {
        return new OrderResult(OrderResultType.OK, order, null);
    }

    public static OrderResult alreadyExist(final String id) {
        return failure(OrderResultType.ALREADY_EXIST, "Order with id " + id + " already exists");
    }

    public static OrderResult notFound(final String orderId) {
        return failure(OrderResultType.NOT_FOUND, "Order with id " + orderId + " not found");
    }

    public static OrderResult invalid(final String errorMessage) {
        return failure(OrderResultType.INVALID, errorMessage);
    }

    public boolean isSuccess() {
        return type == OrderResultType.OK;
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
