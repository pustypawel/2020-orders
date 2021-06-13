package pl.edu.wszib.order.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public PositionDto(@JsonProperty("quantity") Integer quantity,
                       @JsonProperty("product") ProductDto product) {
        this.quantity = quantity;
        this.product = product;
    }
}
