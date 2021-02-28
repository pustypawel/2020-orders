package pl.edu.wszib.orderinfrastructure.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.OrderFacade;

import java.util.Optional;

//@RestController
//@RequestMapping
//@PathVariable
//@RequestParam
//@MatrixVariable

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    // 1. Utworzyć beana żeby spring mógł utworzyć kontroller i uruchomić aplikację
    // 2. Zaimplementować metodę getAll, która zwróci wszystkie zamówienia
    // 3. Zaimplementować metodę create, która utworzy zamówienie
    private final OrderFacade orderFacade;

    @GetMapping
    public Object getAll(@RequestParam final String id) {
        return id;
    }

    @GetMapping("/additional-path")
    public Object getAll2() {
        return "2";
    }

    @GetMapping("/{id}")
    public Object getAll3(@PathVariable final String id) {
        return id;
    }

    @PostMapping
    public Object post(final @RequestBody Object request) {
        return request;
    }
}
