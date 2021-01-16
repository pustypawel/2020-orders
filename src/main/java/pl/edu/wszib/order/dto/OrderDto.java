package pl.edu.wszib.order.dto;

import lombok.Value;
import pl.edu.wszib.order.OrderStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Value
public class OrderDto {
    @NotBlank
    String id;
    @NotEmpty
    List<@Valid PositionDto> positions;
    @NotNull
    OrderStatus status;
}
