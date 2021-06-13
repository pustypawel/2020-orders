package pl.edu.wszib.order.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public ProductDto(@JsonProperty("name") String name,
                      @JsonProperty("price") BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
