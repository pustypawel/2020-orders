package pl.edu.wszib.order.dto;

import lombok.Value;

@Value
public class PositionDto {
    Integer number;
    Integer quantity;
    ProductDto product;
}
