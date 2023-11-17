package christmas.model.ordergroup;

import christmas.model.order.Order;
import java.util.List;

public class OrderGroup {
    private final List<Order> orders;

    protected OrderGroup(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
