package pl.edu.wszib.order;

public class OrderHelper {
    private final OrderFacade orderFacade;

    public OrderHelper(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public Order createCorrectOrder() {
        final Order correctOrder = OrderSamples.sampleOrder();
        orderFacade.create(correctOrder);
        return correctOrder;
    }
}
