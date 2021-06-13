package pl.edu.wszib.order.api;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Value
public class ProductDto {
    @NotBlank
    String name;
    @Positive
    BigDecimal price;
}
