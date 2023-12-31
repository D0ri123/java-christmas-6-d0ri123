package christmas.model.memberdiscount;

import static christmas.model.menu.Menu.Category.MAIN;
import static christmas.util.Constants.MIN_DISCOUNT_AMOUNT;
import static christmas.util.Constants.NOT_APPLIED_DISCOUNT_AMOUNT;

import christmas.model.orderdate.OrderDate;
import christmas.model.menu.MenuService;
import christmas.model.ordergroup.OrderGroup;
import java.util.List;

public class WeekendDiscountService implements DiscountService {
    private static final String DISCOUNT_EVENT = "주말 할인";
    private static final List<String> WEEKEND = List.of("FRIDAY", "SATURDAY");
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2_023;

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
        return orderDate.isQualifiedWeekCondition(WEEKEND);
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= MIN_DISCOUNT_AMOUNT) {
            int discountedMenu = orders.getOrders().stream()
                .mapToInt(order -> MenuService.countMenuWithCategory(MAIN, order.getMenu()) * order.getQuantity())
                .sum();
            return discountedMenu * WEEKEND_DISCOUNT_AMOUNT;
        }
        return NOT_APPLIED_DISCOUNT_AMOUNT;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(DISCOUNT_EVENT, calculateDiscountAmount());
    }
}
