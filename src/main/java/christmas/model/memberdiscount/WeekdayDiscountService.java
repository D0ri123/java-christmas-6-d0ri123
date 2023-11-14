package christmas.model.memberdiscount;

import static christmas.model.menu.Menu.Category.*;

import christmas.model.orderdate.OrderDate;
import christmas.model.menu.MenuService;
import christmas.model.ordergroup.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class WeekdayDiscountService implements DiscountService {
    private static final String eventName = "평일 할인";
    private static final List<String> weekdays = new ArrayList<>(
        List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY"));

    private final OrderDate orderDate;
    private final OrderGroup orders;
    private final int price;

    public WeekdayDiscountService(OrderDate orderDate, OrderGroup orders, int price) {
        this.orderDate = orderDate;
        this.orders = orders;
        this.price = price;
    }

    @Override
    public boolean canApplyDiscount() {
        return weekdays.contains(orderDate.getDay());
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= 10_000) {
            int discountedMenu = orders.getOrders().stream()
                .mapToInt(order -> MenuService.countMenuByCategory(DESSERT, order.getMenu()) * order.getQuantity())
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
