package christmas.model.memberdiscount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.orderdate.OrderDateService;
import christmas.model.ordergroup.OrderGroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekdayDiscountServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "3",
        "4",
        "5",
        "6",
        "7",
    })
    void 평일할인이_적용되는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        OrderGroupService orderGroupService = new OrderGroupService("티본스테이크-2,해산물파스타-1,초코케이크-2");
        WeekdayDiscountService weekdayDiscountService =
            new WeekdayDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 175_000);

        //when
        boolean expectedResult = weekdayDiscountService.canApplyDiscount();
        int discountPrice = weekdayDiscountService.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(discountPrice, (2023*2));
    }

    @Test
    void 디저트메뉴_개당_할인이_적용된다() {
        //given
        OrderDateService orderDateService = new OrderDateService("3");
        OrderGroupService orderGroupService = new OrderGroupService("티본스테이크-2,해산물파스타-1,초코케이크-2");
        WeekdayDiscountService weekdayDiscountService =
            new WeekdayDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 175_000);

        //when
        int discountPrice = weekdayDiscountService.calculateDiscountAmount();

        //then
        assertEquals(2023, discountPrice/2);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3",
        "4",
        "5",
        "6",
        "7",
    })
    void 평일할인이_적용되지만_디저트가_없는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        OrderGroupService orderGroupService = new OrderGroupService("티본스테이크-2,해산물파스타-1,레드와인-1");
        WeekdayDiscountService weekdayDiscountService =
            new WeekdayDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 205_000);

        //when
        boolean expectedResult = weekdayDiscountService.canApplyDiscount();
        int discountPrice = weekdayDiscountService.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(discountPrice, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "2"
    })
    void 평일할인이_적용되지_않는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        OrderGroupService orderGroupService = new OrderGroupService("티본스테이크-2,해산물파스타-1,초코케이크-2");
        WeekdayDiscountService weekdayDiscountService =
            new WeekdayDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 175_000);

        //when
        boolean expectedResult = weekdayDiscountService.canApplyDiscount();
        int discountPrice = weekdayDiscountService.calculateDiscountAmount();

        //then
        assertFalse(expectedResult);
        assertEquals(discountPrice, 0);
    }
}