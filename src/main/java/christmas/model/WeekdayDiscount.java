package christmas.model;

import static christmas.model.domain.Menu.Category.*;

import christmas.model.domain.Menu;
import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class WeekdayDiscount implements Discount {
    private static final String eventName = "평일 할인";
    private static final List<String> weekdays = new ArrayList<>(
        List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY"));

    private final boolean applicability;
    private final int appliedPrice;

    public WeekdayDiscount(OrderDate orderDate, OrderGroup orders) {
        this.applicability = canApplyDiscount(orderDate);
        this.appliedPrice = calculateDiscountAmount(orderDate, orders);
    }

    @Override
    public boolean canApplyDiscount(OrderDate orderDate) {
        return weekdays.contains(orderDate.getDay());
    }

    @Override
    public int calculateDiscountAmount(OrderDate orderDate, OrderGroup orders) {
        int discountedMenu = orders.getOrders().stream()
            .mapToInt(order -> Menu.countMenu(DESSERT, order.getMenu()) * order.getQuantity())
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
