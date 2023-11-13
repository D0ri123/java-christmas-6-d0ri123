package christmas.model;

import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;

public class XmasDiscount implements Discount {
    private static final String eventName = "크리스마스 디데이 할인";
    private final boolean applicability;
    private final int appliedPrice;

    public XmasDiscount(OrderDate orderDate, OrderGroup orders) {
        this.applicability = canApplyDiscount(orderDate);
        this.appliedPrice = calculateDiscountAmount(orderDate, orders);
    }

    @Override
    public boolean canApplyDiscount(OrderDate orderDate) {
        int date = orderDate.getDate();
        return date >= 1 && date <= 25;
    }

    @Override
    public int calculateDiscountAmount(OrderDate orderDate, OrderGroup orders) {
        return 1000 + ((orderDate.getDate() - 1) * 100);
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
