package pl.edu.wszib.order;

import lombok.AllArgsConstructor;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderValidator {
    private final Validator validator;

    public Optional<OrderResult> validate(final OrderDto orderDto) {
        final Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
        if (violations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(incorrect(violations));
    }

    public Optional<OrderResult> validate(final PositionDto position) {
        final Set<ConstraintViolation<PositionDto>> violations = validator.validate(position);
        if (violations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(incorrect(violations));
    }

    private <TType> OrderResult incorrect(final Set<ConstraintViolation<TType>> violations) {
        return OrderResult.invalid(prepareErrorMessage(violations));
    }

    private <TType> String prepareErrorMessage(final Set<ConstraintViolation<TType>> violations) {
        return violations.stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.joining(","));
    }
}
