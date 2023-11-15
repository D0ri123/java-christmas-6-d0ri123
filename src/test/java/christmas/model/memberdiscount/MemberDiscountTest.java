package christmas.model.memberdiscount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MemberDiscountTest {

    @ParameterizedTest
    @CsvSource(value = {
        "0,false",
        "120_000,true"
    })
    void 할인되는_가격이_0원이면_적용되지_않는_할인조건이다(int price, boolean isApplicable) {
        //given
        MemberDiscount memberDiscount = new MemberDiscount("주말 할인", price);

        //when
        boolean expectedResult = memberDiscount.isDiscountApplicable();

        //then
        assertEquals(expectedResult, isApplicable);
    }

}