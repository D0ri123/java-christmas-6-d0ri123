package christmas.model.memberdiscount;

import static christmas.util.Constants.MIN_DISCOUNT_AMOUNT;
import static christmas.util.Constants.NOT_APPLIED_DISCOUNT_AMOUNT;

import christmas.model.orderdate.OrderDate;

public class XmasDiscountService implements DiscountService {
    private static final String DISCOUNT_EVENT = "크리스마스 디데이 할인";
    private static final int EVENT_START = 1;
    private static final int EVENT_END = 25;
    private static final int BASE_AMOUNT = 1_000;
    private static final int XMAS_DISCOUNT_AMOUNT = 100;

    private final OrderDate orderDate;
    private final int price;

    public XmasDiscountService(OrderDate orderDate, int price) {
        this.orderDate = orderDate;
        this.price = price;
    }

    @Override
    public boolean canApplyDiscount() {
        int date = orderDate.getDate();
        return date >= EVENT_START && date <= EVENT_END;
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= MIN_DISCOUNT_AMOUNT) {
            return BASE_AMOUNT + (calculateDay(orderDate.getDate()) * XMAS_DISCOUNT_AMOUNT);
        }
        return NOT_APPLIED_DISCOUNT_AMOUNT;
    }

    private int calculateDay(int date) {
        return date - 1;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(DISCOUNT_EVENT, calculateDiscountAmount());
    }
}
