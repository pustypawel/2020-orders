package pl.edu.wszib.order.api;

import lombok.Value;

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
