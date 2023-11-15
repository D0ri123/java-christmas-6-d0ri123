package christmas.model.memberbenefit;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.freebie.Freebie;
import christmas.model.memberdiscount.MemberDiscount;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MemberBenefitServiceTest {
    private MemberBenefitService memberBenefitService;
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
    }

    @Test
    void MemberBenefit을_생성한다() {
        //given
        memberBenefitService = new MemberBenefitService(discounts, Freebie.FREE_CHAMPAGNE);

        //when
        List<MemberDiscount> appliedServices = memberBenefitService.getMemberDiscountServices();

        //then
        assertEquals(appliedServices.size(), 3);
        assertFalse(appliedServices.contains(weekdayDiscount));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "FREE_CHAMPAGNE,25_000",
        "FREE_NONE,0"
    })
    void 증정품을_포함하여_혜택받는_비용을_모두_더한다(Freebie freebie, int price) {
        //given
        memberBenefitService = new MemberBenefitService(discounts, freebie);

        //when
        int totalAppliedBenefit = memberBenefitService.calculateTotalAppliedBenefit();
        int expectedPrice = 10_046 + price;

        //then
        assertEquals(expectedPrice, totalAppliedBenefit);
    }

    @Test
    void 증정품을_제외하고_혜택받는_비용을_모두_더한다() {
        //given
        memberBenefitService = new MemberBenefitService(discounts, Freebie.FREE_CHAMPAGNE);

        //when
        int expectedPrice = memberBenefitService.calculateTotalAppliedDiscount();

        //then
        assertEquals(expectedPrice, 10_046);
    }

    @Test
    void 증정품이_없다면_null을_반환한다() {
        //given
        memberBenefitService = new MemberBenefitService(discounts, Freebie.FREE_NONE);

        //when
        Freebie expectedFreebie = memberBenefitService.getFreebieOrNull();

        //then
        assertNull(expectedFreebie);
    }
}