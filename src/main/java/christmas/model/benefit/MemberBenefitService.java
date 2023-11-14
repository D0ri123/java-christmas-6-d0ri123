package christmas.model.benefit;

import christmas.model.freebie.Freebie;
import christmas.model.domain.MemberDiscount;

public class MemberBenefitService {
    private final MemberBenefit memberBenefit;

    public MemberBenefitService(MemberBenefit memberBenefit) {
        this.memberBenefit = memberBenefit;
    }

    public int getTotalAppliedBenefit() {
        int totalDiscount = memberBenefit.getAppliedDiscount().stream()
            .mapToInt(MemberDiscount::getAppliedPrice)
            .sum();
        int freebiePrice = memberBenefit.getFreebie().getPrice();
        return totalDiscount + freebiePrice;
    }

    public int getTotalAppliedDiscount() {
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
}
