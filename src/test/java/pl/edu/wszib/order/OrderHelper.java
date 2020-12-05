package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;

public class OrderHelper {
    private final OrderFacade orderFacade;

    public OrderHelper(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public OrderDto createCorrectOrder() {
        final OrderDto correctOrder = OrderSamples.sampleOrder();
        orderFacade.create(correctOrder);
        return correctOrder;
    }
}
