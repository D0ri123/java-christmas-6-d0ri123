package christmas.model.benefit;

import christmas.model.discount.MemberDiscount;
import christmas.model.freebie.Freebie;
import java.util.List;

public class MemberBenefit {
    private final List<MemberDiscount> appliedDiscountService;
    private final Freebie freebie;

    public MemberBenefit(List<MemberDiscount> discountServices, Freebie freebie) {
        this.appliedDiscountService = discountServices;
        this.freebie = freebie;
    }

    public List<MemberDiscount> getAppliedDiscount() {
        return appliedDiscountService;
    }

    public Freebie getFreebie() {
        return freebie;
    }
}
