package christmas.model.memberdiscount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.orderdate.OrderDateService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "3",
        "10",
        "17",
        "24",
        "25",
        "31"
    })
    void 스페셜할인이_적용되는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        SpecialDiscountService specialDiscount = new SpecialDiscountService(orderDateService.getOrderDate(),
            15_000);

        //when
        boolean expectedResult = specialDiscount.canApplyDiscount();
        int expectedPrice = specialDiscount.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(expectedPrice, 1000);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3",
        "10",
        "17",
        "24",
        "25",
        "31"
    })
    void 할인조건에_해당하지만_주문금액이_10000원보다_작은_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        SpecialDiscountService specialDiscount = new SpecialDiscountService(orderDateService.getOrderDate(),
            9_900);

        //when
        boolean expectedResult = specialDiscount.canApplyDiscount();
        int expectedPrice = specialDiscount.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(expectedPrice, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "2",
        "11",
        "16",
        "26",
        "28",
        "30"
    })
    void 스페셜할인이_적용되지_않는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        SpecialDiscountService specialDiscount = new SpecialDiscountService(orderDateService.getOrderDate(),
            9_900);

        //when
        boolean expectedResult = specialDiscount.canApplyDiscount();
        int expectedPrice = specialDiscount.calculateDiscountAmount();

        //then
        assertFalse(expectedResult);
        assertEquals(expectedPrice, 0);
    }
}