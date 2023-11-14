package christmas.model.order;

import christmas.util.validator.OrderValidator;

public class OrderService {
    private final Order order;

    public OrderService(String menu) {
        this.order = generateOrder(menu);
    }

    private Order generateOrder(String menu) {
        String[] split = menu.split("-");
        OrderValidator.validate(split[0], Integer.parseInt(split[1]));
        return new Order(split[0], Integer.parseInt(split[1]));
    }

    public Order getOrder() {
        return order;
    }
}
