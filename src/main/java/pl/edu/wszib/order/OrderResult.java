package pl.edu.wszib.order;

import lombok.Value;
import pl.edu.wszib.order.dto.OrderDto;

@Value
public class OrderResult {
    Type type;
    OrderDto order;

    public static OrderResult failure(Type type) {
        if (type == Type.OK) {
            throw new IllegalArgumentException("type can't be " + Type.OK);
        }
        return new OrderResult(type, null);
    }

    public static OrderResult success(OrderDto order) {
        return new OrderResult(Type.OK, order);
    }

    public enum Type {
        OK,
        ALREADY_EXIST,
        NOT_FOUND;

    }

    public boolean isSuccess() {
        return type == Type.OK;
    }

    public boolean isFailure() {
        return !isSuccess();
    }
}
