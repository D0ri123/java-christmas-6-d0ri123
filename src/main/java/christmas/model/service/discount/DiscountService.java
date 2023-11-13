package christmas.model.service.discount;

import christmas.model.domain.MemberDiscount;

public interface DiscountService {
    boolean canApplyDiscount();
    int calculateDiscountAmount();
    MemberDiscount getMemberDiscount();
}
