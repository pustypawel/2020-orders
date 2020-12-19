package pl.edu.wszib.order;

import lombok.Value;

@Value
class OrderDomainResult {
    OrderResultType type;
    Order order;

    public static OrderDomainResult failure(OrderResultType type) {
        if (type == OrderResultType.OK) {
            throw new IllegalArgumentException("type can't be " + OrderResultType.OK);
        }
        return new OrderDomainResult(type, null);
    }

    public static OrderDomainResult success(Order order) {
        return new OrderDomainResult(OrderResultType.OK, order);
    }

    public boolean isSuccess() {
        return type == OrderResultType.OK;
    }

    public boolean isFailure() {
        return !isSuccess();
    }
}
