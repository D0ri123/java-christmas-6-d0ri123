package christmas.model.order;

import christmas.util.validator.OrderValidator;

public class OrderService {
    private final Order order;

    public OrderService(String menu) {
        this.order = generateOrder(menu);
    }

    private Order generateOrder(String menu) {
        String menuName = menu.split("-")[0];
        int menuQuantity = Integer.parseInt(menu.split("-")[1]);

        OrderValidator.validate(menuName, menuQuantity);

        return new Order(menuName, menuQuantity);
    }

    public Order getOrder() {
        return order;
    }
}
