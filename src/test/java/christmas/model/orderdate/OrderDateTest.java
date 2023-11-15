package christmas.model.orderdate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderDateTest {
    private static final List<Integer> SPECIAL_DISCOUNT_DAYS = List.of(3,10,17,24,25,31);
    private static final List<String> WEEKDAY = List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY");
    private static final List<String> WEEKEND = List.of("FRIDAY", "SATURDAY");
    private static final int EVENT_START = 1;
    private static final int EVENT_END = 25;

    @ParameterizedTest
    @CsvSource(value = {
        "1,FRIDAY,false",
        "3,SUNDAY,true",
        "28,THURSDAY,false"
    })
    void 스페셜할인_조건이_충족되지_않으면_false를_반환한다(int date, String day, boolean result) {
        //given
        OrderDate orderDate = new OrderDate(date, day);

        //when
        boolean expectedResult = orderDate.isQualifiedSpecialCondition(SPECIAL_DISCOUNT_DAYS);

        //then
        assertEquals(expectedResult, result);
    }


    @ParameterizedTest
    @CsvSource(value = {
        "1,FRIDAY,false",
        "3,SUNDAY,true",
        "28,THURSDAY,true"
    })
    void 평일할인_조건이_충족되지_않으면_false를_반환한다(int date, String day, boolean result) {
        //given
        OrderDate orderDate = new OrderDate(date, day);

        //when
        boolean expectedResult = orderDate.isQualifiedWeekCondition(WEEKDAY);

        //then
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,FRIDAY,true",
        "3,SUNDAY,false",
        "28,THURSDAY,false"
    })
    void 주말할인_조건이_충족되지_않으면_false를_반환한다(int date, String day, boolean result) {
        //given
        OrderDate orderDate = new OrderDate(date, day);

        //when
        boolean expectedResult = orderDate.isQualifiedWeekCondition(WEEKEND);

        //then
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,FRIDAY,true",
        "3,SUNDAY,true",
        "28,THURSDAY,false"
    })
    void 크리스마스_할인_조건이_충족되지_않으면_false를_반환한다(int date, String day, boolean result) {
        //given
        OrderDate orderDate = new OrderDate(date, day);

        //when
        boolean expectedResult = orderDate.isQualifiedXmasCondition(EVENT_START, EVENT_END);

        //then
        assertEquals(expectedResult, result);
    }
}