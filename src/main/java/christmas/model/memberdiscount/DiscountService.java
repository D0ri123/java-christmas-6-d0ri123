package christmas.model.memberdiscount;

public interface DiscountService {
    boolean canApplyDiscount();

    int calculateDiscountAmount();

    MemberDiscount getMemberDiscount();
}
