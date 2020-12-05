package pl.edu.wszib.order.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductDto {
    String name;
    BigDecimal price;
}
