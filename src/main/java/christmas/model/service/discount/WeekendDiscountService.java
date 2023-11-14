package christmas.model.service.discount;

import static christmas.model.menu.Menu.Category.MAIN;

import christmas.model.domain.MemberDiscount;
import christmas.model.menu.Menu;
import christmas.model.date.OrderDate;
import christmas.model.ordergroup.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class WeekendDiscountService implements DiscountService {
    private static final String eventName = "주말 할인";
    private static final List<String> weekends = new ArrayList<>(List.of("FRIDAY", "SATURDAY"));

    private final OrderDate orderDate;
    private final OrderGroup orders;
    private final int price;

    public WeekendDiscountService(OrderDate orderDate, OrderGroup orders, int price) {
        this.orderDate = orderDate;
        this.orders = orders;
        this.price = price;
    }

    @Override
    public boolean canApplyDiscount() {
        return weekends.contains(orderDate.getDay());
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= 10_000) {
            int discountedMenu = orders.getOrders().stream()
                .mapToInt(order -> Menu.countMenu(MAIN, order.getMenu()) * order.getQuantity())
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
