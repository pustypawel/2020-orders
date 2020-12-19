package pl.edu.wszib.order;

import lombok.Value;
import pl.edu.wszib.order.dto.OrderDto;

@Value
class OrderDomainResult {
    OrderResultType type;
    Order order;

    public static OrderResult failure(OrderResultType type) {
        if (type == OrderResultType.OK) {
            throw new IllegalArgumentException("type can't be " + OrderResultType.OK);
        }
        return new OrderResult(type, null);
    }

    public static OrderResult success(OrderDto order) {
        return new OrderResult(OrderResultType.OK, order);
    }
    
    public boolean isSuccess() {
        return type == OrderResultType.OK;
    }

    public boolean isFailure() {
        return !isSuccess();
    }
}
