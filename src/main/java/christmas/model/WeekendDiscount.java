package christmas.model;

import static christmas.model.domain.Menu.Category.MAIN;

import christmas.model.domain.Menu;
import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class WeekendDiscount implements Discount {
    private static final String eventName = "주말 할인";
    private static final List<String> weekends = new ArrayList<>(List.of("FRIDAY", "SATURDAY"));

    private final boolean applicability;
    private final int appliedPrice;

    public WeekendDiscount(OrderDate orderDate, OrderGroup orders) {
        this.applicability = canApplyDiscount(orderDate);
        this.appliedPrice = calculateDiscountAmount(orderDate, orders);
    }

    @Override
    public boolean canApplyDiscount(OrderDate orderDate) {
        return weekends.contains(orderDate.getDay());
    }

    @Override
    public int calculateDiscountAmount(OrderDate orderDate, OrderGroup orders) {
        int discountedMenu = orders.getOrders().stream()
            .mapToInt(order -> Menu.countMenu(MAIN, order.getMenu()) * order.getQuantity())
            .sum();
        return discountedMenu * 2023;
    }

    @Override
    public boolean getApplicability() {
        return applicability;
    }

    @Override
    public int getAppliedPrice() {
        return appliedPrice;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

}
