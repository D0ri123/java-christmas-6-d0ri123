package christmas.model.orderdate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderDateServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1,1,FRIDAY",
        "2,2,SATURDAY",
        "25,25,MONDAY",
        "12,12,TUESDAY"
    })
    void OrderDate를_생성한다(String stringDate, int numberDate, String day) {
        //given
        OrderDateService orderDateService = new OrderDateService(stringDate);

        //when
        OrderDate orderDate = orderDateService.getOrderDate();

        //then
        assertEquals(orderDate.getDate(), numberDate);
        assertEquals(orderDate.getDay(), day);
    }

    @Test
    void 공백으로_입력하는_경우() {
        //given, when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new OrderDateService(""));

        //then
        assertEquals(e.getMessage(), "[ERROR] 날짜를 필수로 입력해야 합니다. 다시 입력해 주세요.");
    }

    @Test
    void 숫자만_입력하지_않는_경우() {
        //given, when
        NumberFormatException e = assertThrows(NumberFormatException.class,
            () -> new OrderDateService("1sasd"));

        //then
        assertEquals(e.getMessage(), "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @Test
    void _12월에_존재하지_않는_날짜일_경우() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new OrderDateService("32"));

        assertEquals(e.getMessage(), "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}