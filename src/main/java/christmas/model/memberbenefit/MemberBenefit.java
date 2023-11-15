package christmas.model.memberbenefit;

import christmas.model.memberdiscount.MemberDiscount;
import christmas.model.freebie.Freebie;
import java.util.List;

public class MemberBenefit {
    private final List<MemberDiscount> memberDiscounts;
    private final Freebie freebie;

    public MemberBenefit(List<MemberDiscount> memberDiscounts, Freebie freebie) {
        this.memberDiscounts = memberDiscounts;
        this.freebie = freebie;
    }

    public List<MemberDiscount> getAppliedDiscount() {
        return memberDiscounts;
    }

    public Freebie getFreebie() {
        return freebie;
    }
}
