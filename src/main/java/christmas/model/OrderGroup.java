package christmas.model;

import java.util.ArrayList;
import java.util.List;

public class OrderGroup {
    private List<Order> orders;

    public OrderGroup(String inputOrder) {
        this.orders = generateGroup(inputOrder);
    }

    private List<Order> generateGroup(String inputOrder) {
        orders = new ArrayList<>();
        for (String food : inputOrder.split(",")) {
            int index = food.indexOf("-");
            String foodName = food.substring(0, index);
            int foodQuantity = Integer.parseInt(food.substring(index + 1));

            orders.add(new Order(foodName, foodQuantity));
        }
        return orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
