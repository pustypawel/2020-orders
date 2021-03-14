package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderResult;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping("/{orderId}")
    public Optional<OrderDto> get(final @PathVariable String orderId) {
        return orderFacade.get(orderId);
    }

    @PostMapping("{orderId}/submit")
    public OrderResult submit(final @PathVariable String orderId) {
        return orderFacade.submit(orderId);
    }

    @PutMapping("{orderId}")
    public OrderResult submit(final @PathVariable String orderId,
                              final @RequestBody @Valid OrderDto request) {
        if (Objects.equals(orderId, request.getId())) {
            return orderFacade.update(request);
        } else {
            throw new RuntimeException("Unexpected orderId difference. param = " + orderId + " in body = " + request.getId());
        }
    }

    @PostMapping("{orderId}/positions")
    public OrderResult addPosition(final @PathVariable String orderId,
                                   final @RequestBody @Valid PositionDto position) {
        return orderFacade.addPosition(orderId, position);
    }

    @DeleteMapping("{orderId}/positions/{positionNumber}")
    public OrderResult addPosition(final @PathVariable String orderId,
                                   final @PathVariable Integer positionNumber) {
        return orderFacade.removePosition(orderId, positionNumber);
    }

    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderFacade.getAll();
    }

    @PostMapping
    public OrderResult create(final @RequestBody @Valid OrderDto request) {
        return orderFacade.create(request);
    }

}
