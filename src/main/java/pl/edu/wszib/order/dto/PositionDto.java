package pl.edu.wszib.order.dto;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Value
public class PositionDto {
    @PositiveOrZero
    Integer number;
    @Positive
    Integer quantity;
    @NotNull
    @Valid
    ProductDto product;
}
