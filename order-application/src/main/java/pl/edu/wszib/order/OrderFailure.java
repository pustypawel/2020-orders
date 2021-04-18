package pl.edu.wszib.order;

import lombok.Value;

@Value
public class OrderFailure {
    OrderFailureType type;
    String message;

    public static OrderFailure notFound(String id) {
        return new OrderFailure(OrderFailureType.NOT_FOUND, "Order with id " + id + " not found");
    }

    public static OrderFailure alreadyExist(String id) {
        return new OrderFailure(OrderFailureType.ALREADY_EXIST, "Order with id " + id + " already exists");
    }

    public static OrderFailure alreadySubmitted(String id) {
        return new OrderFailure(OrderFailureType.ALREADY_SUBMITTED, "Order" + id + " already submitted");
    }

    public static OrderFailure positionNotFound(String id,
                                                Integer positionNumber) {
        return new OrderFailure(OrderFailureType.POSITION_NOT_FOUND, "Order " + id + " position " + positionNumber + " not found");
    }
}
