package christmas.model.ordergroup;

import christmas.model.order.Order;
import christmas.model.order.OrderService;
import christmas.util.validator.OrderGroupValidator;
import java.util.ArrayList;
import java.util.List;

public class OrderGroupService {
    private final OrderGroup orders;

    public OrderGroupService(String orderMenu) {
        this.orders = generateOrderGroup(orderMenu);
    }

    private OrderGroup generateOrderGroup(String orderMenu) {
        List<Order> orders = new ArrayList<>();
        OrderGroupValidator.validateInputTemplate(orderMenu);
        for (String menu : orderMenu.split(",")) {
            OrderService orderService = new OrderService(menu);
            orders.add(orderService.getOrder());
        }
        OrderGroupValidator.validateOrderGroup(orders);
        return new OrderGroup(orders);
    }

    public OrderGroup getOrders() {
        return orders;
    }
}
