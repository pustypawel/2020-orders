package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;

public class OrderHelper {
    private final OrderFacade orderFacade;

    public OrderHelper(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public OrderDto createCorrectOrder() {
        final OrderDto correctOrder = OrderSamples.sampleOrder();
        return orderFacade.create(correctOrder)
                .get();
    }

    public OrderDto createSubmittedOrder() {
        final String orderId = createCorrectOrder().getId();
        return orderFacade.submit(orderId).getOrder().get();
    }
}
