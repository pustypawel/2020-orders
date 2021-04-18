package pl.edu.wszib.order;

import lombok.Value;

import java.util.Optional;

@Value
class OrderDomainResult {
    OrderFailureType type;
    Order order;
    String errorMessage;

    public static OrderDomainResult failure(final OrderFailureType type,
                                            final String errorMessage) {
        if (type == OrderFailureType.OK) {
            throw new IllegalArgumentException("type can't be " + OrderFailureType.OK);
        }
        return new OrderDomainResult(type, null, errorMessage);
    }

    public static OrderDomainResult success(final Order order) {
        return new OrderDomainResult(OrderFailureType.OK, order, null);
    }

    public static OrderDomainResult alreadySubmitted(final String id) {
        return OrderDomainResult.failure(OrderFailureType.ALREADY_SUBMITTED, "Order" + id + " already submitted");
    }

    public static OrderDomainResult positionNotFound(final String id,
                                                     final Integer positionNumber) {
        return failure(OrderFailureType.POSITION_NOT_FOUND, "Order " + id + " position " + positionNumber + " not found");
    }

    public boolean isSuccess() {
        return type == OrderFailureType.OK;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public OrderResult toFailureApi() {
        return OrderResult.failure(type, errorMessage);
    }

    public OrderResult toSuccessApi() {
        return OrderResult.success(order.toDto());
    }

    public Optional<Order> getOrder() {
        return Optional.ofNullable(order);
    }

    public Optional<String> getErrorMessage() {
        return Optional.ofNullable(errorMessage);
    }
}
