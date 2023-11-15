package christmas.model.memberdiscount;

import static christmas.util.Constants.MIN_DISCOUNT_AMOUNT;
import static christmas.util.Constants.NOT_APPLIED_DISCOUNT_AMOUNT;

import christmas.model.orderdate.OrderDate;
import java.util.List;

public class SpecialDiscountService implements DiscountService {
    private static final String DISCOUNT_EVENT = "특별 할인";
    private static final List<Integer> SPECIAL_DISCOUNT_DAYS = List.of(3,10,17,24,25,31);
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;

    private final OrderDate orderDate;
    private final int price;

    public SpecialDiscountService(OrderDate orderDate, int price) {
        this.orderDate = orderDate;
        this.price = price;
    }

    @Override
    public boolean canApplyDiscount() {
        return orderDate.isQuailfiedCondition(SPECIAL_DISCOUNT_DAYS);
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= MIN_DISCOUNT_AMOUNT) {
            return SPECIAL_DISCOUNT_AMOUNT;
        }
        return NOT_APPLIED_DISCOUNT_AMOUNT;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(DISCOUNT_EVENT, calculateDiscountAmount());
    }
}
