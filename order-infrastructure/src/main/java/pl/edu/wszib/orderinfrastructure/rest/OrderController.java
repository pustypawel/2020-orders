package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.dto.OrderDto;
import pl.edu.wszib.order.dto.PositionDto;

import javax.validation.Valid;
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
    public OrderDto update(final @PathVariable String orderId) {
        return orderFacade.submit(orderId)
                .get(); //TODO Either.left obsługa
    }

    @PutMapping("{orderId}")
    public OrderDto update(final @PathVariable String orderId,
                           final @RequestBody @Valid OrderDto request) {
        if (Objects.equals(orderId, request.getId())) {
            return orderFacade.update(request)
                    .get(); //TODO Either.left obsługa
        } else {
            throw new RuntimeException("Unexpected orderId difference. param = " + orderId + " in body = " + request.getId());
        }
    }

    @PostMapping("{orderId}/positions")
    public OrderDto addPosition(final @PathVariable String orderId,
                                final @RequestBody @Valid PositionDto position) {
        return orderFacade.addPosition(orderId, position)
                .get(); //TODO Either.left obsługa
    }

    @DeleteMapping("{orderId}/positions/{positionNumber}")
    public OrderDto addPosition(final @PathVariable String orderId,
                                final @PathVariable Integer positionNumber) {
        return orderFacade.removePosition(orderId, positionNumber)
                .get(); //TODO Either.left obsługa
    }

    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderFacade.getAll();
    }

    @PostMapping
    public OrderDto create(final @RequestBody @Valid OrderDto request) {
        return orderFacade.create(request)
                .get(); //TODO Either.left obsługa
    }

}
