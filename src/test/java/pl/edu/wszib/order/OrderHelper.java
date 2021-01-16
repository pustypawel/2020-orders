package pl.edu.wszib.order;

import pl.edu.wszib.order.dto.OrderDto;

public class OrderHelper {
    private final OrderFacade orderFacade;

    public OrderHelper(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public OrderDto createCorrectOrder() {
        final OrderDto correctOrder = OrderSamples.sampleOrder();
        final OrderResult result = orderFacade.create(correctOrder);
        if (result.isFailure()) {
            throw new IllegalStateException("Failure = " + result);
        }
        return result.getOrder().get();
    }

    public OrderDto createSubmittedOrder() {
        final String orderId = createCorrectOrder().getId();
        final OrderResult result = orderFacade.submit(orderId);
        if (result.isFailure()) {
            throw new IllegalStateException("Failure = " + result);
        }
        return result.getOrder().get();
    }
}
