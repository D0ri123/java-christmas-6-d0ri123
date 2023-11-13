package christmas.model.domain;

import christmas.model.DiscountService;
import java.util.List;
import java.util.stream.Collectors;

public class MemberBenefit {
    private final List<DiscountService> appliedDiscountService;
    private final Freebie freebie;

    public MemberBenefit(List<DiscountService> discountServices, Freebie freebie) {
        this.appliedDiscountService = filterOnlyAppliedDiscount(discountServices);
        this.freebie = freebie;
    }

    private List<DiscountService> filterOnlyAppliedDiscount(List<DiscountService> discountServices) {
        return discountServices.stream()
            .filter(DiscountService::getApplicability)
            .filter(discount -> discount.getAppliedPrice() != 0)
            .collect(Collectors.toList());
    }

    public List<DiscountService> getAppliedDiscount() {
        return appliedDiscountService;
    }

    public Freebie getFreebie() {
        return freebie;
    }
}
