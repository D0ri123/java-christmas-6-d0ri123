package christmas.model.memberdiscount;

public class MemberDiscount {
    private final String discountEvent;
    private final int appliedPrice;

    public MemberDiscount(String discountEvent, int appliedPrice) {
        this.discountEvent = discountEvent;
        this.appliedPrice = appliedPrice;
    }

    public boolean isDiscountApplicable() {
        return appliedPrice != 0;
    }

    public String getEventName() {
        return discountEvent;
    }

    public int getAppliedPrice() {
        return appliedPrice;
    }
}
