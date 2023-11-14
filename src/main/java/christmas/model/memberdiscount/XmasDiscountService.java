package christmas.model.memberdiscount;

import christmas.model.orderdate.OrderDate;

public class XmasDiscountService implements DiscountService {
    private static final String eventName = "크리스마스 디데이 할인";

    private final OrderDate orderDate;
    private final int price;

    public XmasDiscountService(OrderDate orderDate, int price) {
        this.orderDate = orderDate;
        this.price = price;
    }

    @Override
    public boolean canApplyDiscount() {
        int date = orderDate.getDate();
        return date >= 1 && date <= 25;
    }

    @Override
    public int calculateDiscountAmount() {
        if (canApplyDiscount() && price >= 10_000) {
            return 1000 + ((orderDate.getDate() - 1) * 100);
        }
        return 0;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(eventName, calculateDiscountAmount());
    }
}
