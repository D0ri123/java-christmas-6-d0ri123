package christmas.model;

import java.util.List;
import java.util.stream.Collectors;

public class MemberBenefit {

    private final List<Discount> appliedDiscount;
    private final Freebie freebie;

    public MemberBenefit(List<Discount> discounts, Freebie freebie) {
        this.appliedDiscount = filterOnlyAppliedDiscount(discounts);
        this.freebie = freebie;
    }

    public int getTotalAppliedBenefit() {
        int totalDiscount = appliedDiscount.stream()
            .mapToInt(Discount::getAppliedPrice)
            .sum();
        int freebiePrice = freebie.getPrice();
        return totalDiscount + freebiePrice;
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
        if (freebie.getName().equals("없음")) {
            return null;
        }
        return freebie;
    }

    public int getTotalAppliedDiscount() {
        return appliedDiscount.stream()
            .mapToInt(Discount::getAppliedPrice)
            .sum();
    }
}
