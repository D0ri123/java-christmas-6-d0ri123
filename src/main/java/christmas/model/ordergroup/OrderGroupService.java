package christmas.model.ordergroup;

import christmas.model.menu.MenuService;
import christmas.model.order.Order;
import christmas.model.order.OrderService;
import christmas.util.validator.OrderGroupValidator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderGroupService {
    private final OrderGroup orders;

    public OrderGroupService(String orderMenu) {
        this.orders = generateOrderGroup(orderMenu);
    }

    private OrderGroup generateOrderGroup(String orderMenu) {
        OrderGroupValidator.validateInputTemplate(orderMenu);

        List<Order> processedOrder = generateOrders(orderMenu);

        OrderGroupValidator.validateOrderGroup(processedOrder);
        return new OrderGroup(processedOrder);
    }

    private List<Order> generateOrders(String orderMenu) {
        return Arrays.stream(orderMenu.split(","))
            .map(menu -> new OrderService(menu).getOrder())
            .collect(Collectors.toList());
    }

    public int calculateTotalPrice() {
        return orders.getOrders().stream()
            .mapToInt(order -> MenuService.getPriceByFoodName(order.getMenu()) * order.getQuantity())
            .sum();
    }

    public OrderGroup getOrders() {
        return orders;
    }

    public List<Order> getOrderList() {
        return orders.getOrders();
    }
}
