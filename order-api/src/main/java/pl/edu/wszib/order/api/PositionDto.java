package pl.edu.wszib.order.api;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class PositionDto {
    @Positive
    Integer quantity;
    @NotNull
    @Valid
    ProductDto product;
}
