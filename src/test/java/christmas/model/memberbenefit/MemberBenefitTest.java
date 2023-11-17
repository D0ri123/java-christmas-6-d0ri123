package christmas.model.memberbenefit;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.freebie.Freebie;
import christmas.model.memberdiscount.MemberDiscount;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberBenefitTest {
    MemberBenefit memberBenefit;
    List<MemberDiscount> discounts;
    MemberDiscount xmasDiscount;
    MemberDiscount weekdayDiscount;
    MemberDiscount weekendDiscount;
    MemberDiscount specialDiscount;

    @BeforeEach
    void setUp() {
        xmasDiscount = new MemberDiscount("크리스마스 디데이 할인", 5_000);
        weekdayDiscount = new MemberDiscount("평일 할인", 0);
        weekendDiscount = new MemberDiscount("주말 할인", 4_046);
        specialDiscount = new MemberDiscount("특별 할인", 1_000);
        discounts = List.of(xmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount);
        memberBenefit = new MemberBenefit(discounts, Freebie.FREE_CHAMPAGNE);
    }

    @Test
    void 할인혜택_증정혜택의_모든_비용을_더한다() {
        int expectedPrice = memberBenefit.calculateBenefitPrice();
        assertEquals(expectedPrice, 35_046);
    }

}