package christmas.model.service.discount;

import static christmas.model.domain.Menu.Category.*;

import christmas.model.domain.MemberDiscount;
import christmas.model.domain.Menu;
import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class WeekdayDiscountService implements DiscountService {
    private static final String eventName = "평일 할인";
    private static final List<String> weekdays = new ArrayList<>(
        List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY"));

    private final OrderDate orderDate;
    private final OrderGroup orders;

    public WeekdayDiscountService(OrderDate orderDate, OrderGroup orders) {
        this.orderDate = orderDate;
        this.orders = orders;
    }

    @Override
    public boolean canApplyDiscount() {
        return weekdays.contains(orderDate.getDay());
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount()) {
            int discountedMenu = orders.getOrders().stream()
                .mapToInt(order -> Menu.countMenu(DESSERT, order.getMenu()) * order.getQuantity())
                .sum();
            return discountedMenu * 2023;
        }
        return 0;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(eventName, calculateDiscountAmount());
    }
}