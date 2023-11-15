package christmas.model.memberdiscount;

import static christmas.model.menu.Menu.Category.*;
import static christmas.util.Constants.MIN_DISCOUNT_AMOUNT;
import static christmas.util.Constants.NOT_APPLIED_DISCOUNT_AMOUNT;

import christmas.model.orderdate.OrderDate;
import christmas.model.menu.MenuService;
import christmas.model.ordergroup.OrderGroup;
import java.util.List;

public class WeekdayDiscountService implements DiscountService {
    private static final String DISCOUNT_EVENT = "평일 할인";
    private static final List<String> WEEKDAY = List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY");
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2_023;


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
        return WEEKDAY.contains(orderDate.getDay());
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= MIN_DISCOUNT_AMOUNT) {
            int discountedMenu = orders.getOrders().stream()
                .mapToInt(order -> MenuService.countMenuWithCategoryAndName(DESSERT, order.getMenu()) * order.getQuantity())
                .sum();
            return discountedMenu * WEEKDAY_DISCOUNT_AMOUNT;
        }
        return NOT_APPLIED_DISCOUNT_AMOUNT;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(DISCOUNT_EVENT, calculateDiscountAmount());
    }
}
