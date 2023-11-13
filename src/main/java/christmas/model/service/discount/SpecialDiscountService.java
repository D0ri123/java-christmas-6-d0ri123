package christmas.model.service.discount;

import christmas.model.domain.MemberDiscount;
import christmas.model.domain.OrderDate;
import java.util.ArrayList;
import java.util.List;

public class SpecialDiscountService implements DiscountService {
    private static final String eventName = "특별 할인";
    private static final List<Integer> specialDays = new ArrayList<>(List.of(3,10,17,24,25,31));

    private final OrderDate orderDate;

    public SpecialDiscountService(OrderDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean canApplyDiscount() {
        return specialDays.contains(orderDate.getDate());
    }

    @Override
    public int calculateDiscountAmount() {
        if(canApplyDiscount()) {
            return 1000;
        }
        return 0;
    }

    @Override
    public MemberDiscount getMemberDiscount() {
        return new MemberDiscount(eventName, calculateDiscountAmount());
    }
}
