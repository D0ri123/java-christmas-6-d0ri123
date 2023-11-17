package christmas.model.memberbenefit;

import christmas.model.freebie.Freebie;
import christmas.model.memberdiscount.MemberDiscount;
import java.util.List;
import java.util.stream.Collectors;

public class MemberBenefitService {
    private final MemberBenefit memberBenefit;

    public MemberBenefitService(List<MemberDiscount> discountServices, Freebie freebie) {
        List<MemberDiscount> memberDiscounts = filterAppliedDiscount(discountServices);
        this.memberBenefit = new MemberBenefit(memberDiscounts, freebie);
    }

    private List<MemberDiscount> filterAppliedDiscount(List<MemberDiscount> discountServices) {
        return discountServices.stream()
            .filter(MemberDiscount::isDiscountApplicable)
            .collect(Collectors.toList());
    }

    public int calculateTotalAppliedBenefit() {
       return memberBenefit.calculateBenefitPrice();
    }

    public int calculateTotalAppliedDiscount() {
        return memberBenefit.calculateTotalDiscount();
    }

    public Freebie getFreebie() {
        return memberBenefit.getFreebie();
    }

    public List<MemberDiscount> getMemberDiscountServices() {
        return memberBenefit.getAppliedDiscount();
    }
}
