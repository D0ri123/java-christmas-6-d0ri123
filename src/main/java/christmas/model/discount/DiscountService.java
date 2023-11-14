package christmas.model.discount;

public interface DiscountService {
    boolean canApplyDiscount();
    int calculateDiscountAmount();
    MemberDiscount getMemberDiscount();
}
