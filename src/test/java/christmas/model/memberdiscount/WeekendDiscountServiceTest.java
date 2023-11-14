package christmas.model.memberdiscount;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.orderdate.OrderDateService;
import christmas.model.ordergroup.OrderGroupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendDiscountServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "2",
        "8",
        "9"
    })
    void 주말할인이_적용되는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        OrderGroupService orderGroupService =
            new OrderGroupService("티본스테이크-2,해산물파스타-1,초코케이크-2,레드와인-1");
        WeekendDiscountService weekendDiscountService =
            new WeekendDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 235_000);

        //when
        boolean expectedResult = weekendDiscountService.canApplyDiscount();
        int discountPrice = weekendDiscountService.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(discountPrice, (2023*3));
    }

    @Test
    void 메인메뉴_개당_할인이_적용된다() {
        //given
        OrderDateService orderDateService = new OrderDateService("8");
        OrderGroupService orderGroupService = new OrderGroupService("티본스테이크-2,해산물파스타-1,초코케이크-2");
        WeekendDiscountService weekendDiscountService =
            new WeekendDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 175_000);

        //when
        int discountPrice = weekendDiscountService.calculateDiscountAmount();

        //then
        assertEquals(2023, discountPrice/3);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1",
        "2",
        "8",
        "9"
    })
    void 주말할인이_적용되지만_메인요리가_없는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        OrderGroupService orderGroupService = new OrderGroupService("초코케이크-1,레드와인-2");
        WeekendDiscountService weekendDiscountService =
            new WeekendDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 205_000);

        //when
        boolean expectedResult = weekendDiscountService.canApplyDiscount();
        int discountPrice = weekendDiscountService.calculateDiscountAmount();

        //then
        assertTrue(expectedResult);
        assertEquals(discountPrice, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "3",
        "4",
        "5",
        "6",
        "7"
    })
    void 주말할인이_적용되지_않는_경우(String date) {
        //given
        OrderDateService orderDateService = new OrderDateService(date);
        OrderGroupService orderGroupService = new OrderGroupService("티본스테이크-2,해산물파스타-1,초코케이크-2");
        WeekendDiscountService weekendDiscountService =
            new WeekendDiscountService(orderDateService.getOrderDate(), orderGroupService.getOrders(), 175_000);

        //when
        boolean expectedResult = weekendDiscountService.canApplyDiscount();
        int discountPrice = weekendDiscountService.calculateDiscountAmount();

        //then
        assertFalse(expectedResult);
        assertEquals(discountPrice, 0);
    }

}