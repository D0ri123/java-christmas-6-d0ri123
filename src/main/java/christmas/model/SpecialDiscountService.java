package christmas.model;

import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class SpecialDiscountService implements DiscountService {
    private static final String eventName = "특별 할인";
    private static final List<Integer> specialDays = new ArrayList<>(List.of(3,10,17,24,25,31));

    //TODO: MemberDiscount 클래스의 멤버 변수로 지정하기. 이것만 빼놓자
    private final boolean applicability;
    private final int appliedPrice;

    public SpecialDiscountService(OrderDate orderDate, OrderGroup orderGroup) {
        this.applicability = canApplyDiscount(orderDate);
        this.appliedPrice = calculateDiscountAmount(orderDate, orderGroup);
    }

    @Override
    public boolean canApplyDiscount(OrderDate orderDate) {
        return specialDays.contains(orderDate.getDate());
    }

    @Override
    public int calculateDiscountAmount(OrderDate orderDate, OrderGroup orders) {
        return 1000;
    }

    //TODO: Discount는 서비스로 변경하고, 새로운 MemberDiscount 클래스 변경하기
    @Override
    public boolean getApplicability() {
        return applicability;
    }

    @Override
    public int getAppliedPrice() {
        return appliedPrice;
    }

    @Override
    public String getEventName() {
        return eventName;
    }
}
