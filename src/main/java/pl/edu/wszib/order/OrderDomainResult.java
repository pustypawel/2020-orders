package pl.edu.wszib.order;

import lombok.Value;

import java.util.Optional;

@Value
class OrderDomainResult {
    OrderResultType type;
    Order order;
    String errorMessage;

    public static OrderDomainResult failure(final OrderResultType type,
                                            final String errorMessage) {
        if (type == OrderResultType.OK) {
            throw new IllegalArgumentException("type can't be " + OrderResultType.OK);
        }
        return new OrderDomainResult(type, null, errorMessage);
    }

    public static OrderDomainResult success(final Order order) {
        return new OrderDomainResult(OrderResultType.OK, order, null);
    }

    public static OrderDomainResult alreadySubmitted(final String id) {
        return OrderDomainResult.failure(OrderResultType.ALREADY_SUBMITTED, "Order" + id + " already submitted");
    }

    public static OrderDomainResult positionNotFound(final String id,
                                                     final Integer positionNumber) {
        return failure(OrderResultType.POSITION_NOT_FOUND, "Order " + id + " position " + positionNumber + " not found");
    }

    public boolean isSuccess() {
        return type == OrderResultType.OK;
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
