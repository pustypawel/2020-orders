package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;
import pl.edu.wszib.order.dto.OrderDto;

import java.util.Collection;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    // 1. Utworzyć beana żeby spring mógł utworzyć kontroller i uruchomić aplikację
    // 2. Zaimplementować metodę getAll, która zwróci wszystkie zamówienia
    // 3. Zaimplementować metodę create, która utworzy zamówienie
    private final OrderFacade orderFacade;

    @GetMapping
    public Collection<OrderDto> getAll() {
        return orderFacade.getAll();
    }

    @PostMapping
    public Object post(final @RequestBody Object request) {
        return request;
    }
}
