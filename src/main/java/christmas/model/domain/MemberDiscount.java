package christmas.model.domain;

public class MemberDiscount {
    private final String discountEvent;
    private final int appliedPrice;

    public MemberDiscount(String discountEvent, int appliedPrice) {
        this.discountEvent = discountEvent;
        this.appliedPrice = appliedPrice;
    }

    public String getEventName() {
        return discountEvent;
    }

    public int getAppliedPrice() {
        return appliedPrice;
    }
}
