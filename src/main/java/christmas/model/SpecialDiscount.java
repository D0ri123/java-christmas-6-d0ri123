package christmas.model;

import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;
import java.util.ArrayList;
import java.util.List;

public class SpecialDiscount implements Discount {
    private static final String eventName = "특별 할인";
    private static final List<Integer> specialDays = new ArrayList<>(List.of(3,10,17,24,25,31));

    private final boolean applicability;
    private final int appliedPrice;

    public SpecialDiscount(OrderDate orderDate, OrderGroup orderGroup) {
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
