package christmas.model.domain;

import christmas.model.Discount;
import java.util.List;
import java.util.stream.Collectors;

public class MemberBenefit {
    private final List<Discount> appliedDiscount;
    private final Freebie freebie;

    public MemberBenefit(List<Discount> discounts, Freebie freebie) {
        this.appliedDiscount = filterOnlyAppliedDiscount(discounts);
        this.freebie = freebie;
    }

    private List<Discount> filterOnlyAppliedDiscount(List<Discount> discounts) {
        return discounts.stream()
            .filter(Discount::getApplicability)
            .filter(discount -> discount.getAppliedPrice() != 0)
            .collect(Collectors.toList());
    }

    public List<Discount> getAppliedDiscount() {
        return appliedDiscount;
    }

    public Freebie getFreebie() {
        return freebie;
    }
}
