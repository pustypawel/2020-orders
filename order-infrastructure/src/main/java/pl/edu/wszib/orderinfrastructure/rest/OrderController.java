package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderResult;
import pl.edu.wszib.order.dto.OrderDto;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;

    // 1: get one
    // 2: submit
    // 3: update
    // 4: add position
    // 5: remove position

    // GET /api/orders/{orderId}
    // POST /api/orders/{orderId}/submit
    // PUT /api/orders/{orderId}
    // POST /api/orders/{orderId}/positions
    // DELETE /api/orders/{orderId}/positions/{positionNumber}

//    @GetMapping
//    @PostMapping
//    @PutMapping
//    @DeleteMapping

//    @PathVariable
//    @RequestParam

    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderFacade.getAll();
    }

    @PostMapping
    public OrderResult create(final @RequestBody @Valid OrderDto request) {
        return orderFacade.create(request);
    }

}
