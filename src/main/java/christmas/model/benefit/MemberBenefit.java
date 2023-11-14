package christmas.model.benefit;

import christmas.model.discount.MemberDiscount;
import christmas.model.freebie.Freebie;
import java.util.List;
import java.util.stream.Collectors;

public class MemberBenefit {
    private final List<MemberDiscount> appliedDiscountService;
    private final Freebie freebie;

    public MemberBenefit(List<MemberDiscount> discountServices, Freebie freebie) {
        this.appliedDiscountService = filterOnlyAppliedDiscount(discountServices);
        this.freebie = freebie;
    }

    private List<MemberDiscount> filterOnlyAppliedDiscount(List<MemberDiscount> discountServices) {
        return discountServices.stream()
            .filter(discount -> discount.getAppliedPrice() != 0)
            .collect(Collectors.toList());
    }

    public List<MemberDiscount> getAppliedDiscount() {
        return appliedDiscountService;
    }

    public Freebie getFreebie() {
        return freebie;
    }
}
