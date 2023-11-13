package christmas.model.service.discount;

import christmas.model.domain.MemberDiscount;
import christmas.model.domain.OrderDate;

public class XmasDiscountService implements DiscountService {
    private static final String eventName = "크리스마스 디데이 할인";

    private final OrderDate orderDate;

    public XmasDiscountService(OrderDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean canApplyDiscount() {
        int date = orderDate.getDate();
        return date >= 1 && date <= 25;
    }

    @Override
    public int calculateDiscountAmount() {
        if(canApplyDiscount()) {
            return 1000 + ((orderDate.getDate() - 1) * 100);
        }
        return 0;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(eventName, calculateDiscountAmount());
    }
}
