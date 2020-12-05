package pl.edu.wszib.order;

import lombok.EqualsAndHashCode;
import pl.edu.wszib.order.dto.PositionDto;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
class Position {
    private final Integer number;
    private final Integer quantity;
    private final Product product;

    Position(final Integer number,
             final Integer quantity,
             final Product product) {
        this.number = number;
        this.quantity = quantity;
        this.product = product;
    }
    static Position create(final PositionDto position) {
        return new Position(position.getNumber(),
                position.getQuantity(),
                Product.create(position.getProduct()));
    }

    static Set<Position> create(final Set<PositionDto> positions) {
        return positions.stream()
                .map(Position::create)
                .collect(Collectors.toSet());
    }

    PositionDto toDto() {
        return new PositionDto(number, quantity, product.toDto());
    }

    static Set<PositionDto> toDto(Set<Position> positions) {
        return positions.stream()
                .map(Position::toDto)
                .collect(Collectors.toSet());
    }
}
