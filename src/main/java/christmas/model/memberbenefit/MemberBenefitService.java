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
            .filter(discount -> discount.getAppliedPrice() != 0)
            .collect(Collectors.toList());
    }

    //Benefit = 할인 혜택 + 증정 혜택
    public int calculateTotalAppliedBenefit() {
        int totalDiscount = memberBenefit.getAppliedDiscount().stream()
            .mapToInt(MemberDiscount::getAppliedPrice)
            .sum();
        int freebiePrice = memberBenefit.getFreebie().getPrice();
        return totalDiscount + freebiePrice;
    }

    public int calculateTotalAppliedDiscount() {
        return memberBenefit.getAppliedDiscount().stream()
            .mapToInt(MemberDiscount::getAppliedPrice)
            .sum();
    }

    public Freebie getFreebieOrNull() {
        Freebie freebie = memberBenefit.getFreebie();
        if (freebie.getName().equals("없음")) {
            return null;
        }
        return freebie;
    }

    public List<MemberDiscount> getMemberDiscountServices() {
        return memberBenefit.getAppliedDiscount();
    }
}
