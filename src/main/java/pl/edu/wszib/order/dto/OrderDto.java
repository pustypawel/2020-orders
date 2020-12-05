package pl.edu.wszib.order.dto;

import lombok.Value;
import pl.edu.wszib.order.OrderStatus;

import java.util.Set;

@Value
public class OrderDto {
    String id;
    Set<PositionDto> positions;
    OrderStatus status;
}
