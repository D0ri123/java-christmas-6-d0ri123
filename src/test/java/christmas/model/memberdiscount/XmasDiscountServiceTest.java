package christmas.model.memberdiscount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.orderdate.OrderDateService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class XmasDiscountServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1,1000",
        "5,1400",
        "10,1900",
        "25,3400"
    })
    void 크리스마스_디데이_할인이_적용되는_경우(String date, int price) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        XmasDiscountService xmasDiscount = new XmasDiscountService(orderDateService.getOrderDate(), 15_000);

        //when
        boolean expectedResult = xmasDiscount.canApplyDiscount();
        int expectedPrice = xmasDiscount.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(expectedPrice, price);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3",
        "10",
        "17",
        "24",
        "25"
    })
    void 할인조건에_해당하지만_주문금액이_10000원보다_작은_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        XmasDiscountService xmasDiscount = new XmasDiscountService(orderDateService.getOrderDate(), 9_900);

        //when
        boolean expectedResult = xmasDiscount.canApplyDiscount();
        int expectedPrice = xmasDiscount.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(expectedPrice, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "26",
        "27",
        "29"
    })
    void 크리스마스_디데이_할인이_적용되지_않는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        XmasDiscountService xmasDiscount = new XmasDiscountService(orderDateService.getOrderDate(), 155_000);

        //when
        boolean expectedResult = xmasDiscount.canApplyDiscount();
        int expectedPrice = xmasDiscount.calculateDiscountAmount();

        //then
        assertFalse(expectedResult);
        assertEquals(expectedPrice, 0);
    }
}
