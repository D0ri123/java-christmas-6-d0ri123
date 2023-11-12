package christmas.model;

public interface Discount {
    boolean canApplyDiscount(OrderDate orderDate);
    int calculateDiscountAmount(OrderDate orderDate, OrderGroup orders);
    boolean getApplicability();
    int getAppliedPrice();
    String getEventName();
}
