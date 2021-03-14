package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderResult;
import pl.edu.wszib.order.dto.OrderDto;

import java.util.Collection;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderFacade.getAll();
    }

    @PostMapping
    public OrderResult create(final @RequestBody OrderDto request) {
        return orderFacade.create(request);
    }
}
