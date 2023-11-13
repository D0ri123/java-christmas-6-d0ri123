package christmas.model;

import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;

public interface DiscountService {
    boolean canApplyDiscount(OrderDate orderDate);
    int calculateDiscountAmount(OrderDate orderDate, OrderGroup orders);
    boolean getApplicability();
    int getAppliedPrice();
    String getEventName();
}
