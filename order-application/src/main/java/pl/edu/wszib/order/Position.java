package pl.edu.wszib.order;

import lombok.EqualsAndHashCode;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class Position {
    private final Integer quantity;
    private final Product product;

    public Position(final Integer quantity,
                    final Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    static Position create(final PositionDto position) {
        return new Position(position.getQuantity(),
                Product.create(position.getProduct()));
    }

    static List<Position> create(final List<PositionDto> positions) {
        return positions.stream()
                .map(Position::create)
                .collect(Collectors.toList());
    }

    PositionDto toDto() {
        return new PositionDto(quantity, product.toDto());
    }

    static List<PositionDto> toDto(final List<Position> positions) {
        return positions.stream()
                .map(Position::toDto)
                .collect(Collectors.toList());
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
