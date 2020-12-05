package pl.edu.wszib.order.dto;

import lombok.Value;
import pl.edu.wszib.order.OrderStatus;

import java.util.Set;

@Value
public class OrderCreateDto {
    Set<PositionDto> positions;
    OrderStatus status;
}
