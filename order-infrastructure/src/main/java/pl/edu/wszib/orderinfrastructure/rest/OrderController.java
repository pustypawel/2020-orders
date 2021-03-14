package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.OrderResult;
import pl.edu.wszib.order.dto.OrderDto;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderFacade.getAll();
    }

    @PostMapping
    public OrderResult create(final @RequestBody @Valid OrderDto request) {
        return orderFacade.create(request);
    }

}
