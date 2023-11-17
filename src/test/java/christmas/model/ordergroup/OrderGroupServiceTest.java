package christmas.model.ordergroup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderGroupServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "'해산물파스타-2,레드와인-1,초코케이크-1',3",
        "'티본스테이크-2,해산물파스타-1,레드와인-1',3"
    })
    void OrderGroup_생성한다(String orderMenu, int quantity) {
        //given
        OrderGroupService orderGroupService = new OrderGroupService(orderMenu);

        //when
        OrderGroup orders = orderGroupService.getOrders();

        //then
        assertEquals(orders.getOrders().size(), quantity);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "' '",
        "'해산물파스타:2,레드와인:1,초코케이크:1'",
        "'티본스테이크-2, 해산물파스타-1, 레드와인-1'",
        "'아이스크림 2, 바비큐립 3'",
        "'크리스마스파스타-3,초코케이크-2,샴페인-3,'"
    })
    void 형식에_맞지_않게_입력하는_경우(String orderMenu) {
        //given, when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new OrderGroupService(orderMenu));

        //then
        assertEquals(e.getMessage(), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "'해산물파스타-2,레드와인-1,초코케이크-1,레드와인-2'",
        "'크리스마스파스타-1,크리스마스파스타-2,초코케이크-2,샴페인-3'"
    })
    void 중복된_메뉴가_있을_경우(String orderMenu) {
        //given, when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new OrderGroupService(orderMenu));

        //then
        assertEquals(e.getMessage(), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "'해산물파스타-10,초코케이크-18,레드와인-1'",
        "'크리스마스파스타-10,초코케이크-10,샴페인-10'"
    })
    void 총_주문개수가_20개_초과한_경우(String orderMenu) {
        //given, when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new OrderGroupService(orderMenu));

        //then
        assertEquals(e.getMessage(), "[ERROR] 총 주문 개수가 20개를 초과했습니다. 다시 입력해 주세요.");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "'레드와인-2,제로콜라-5'",
        "'샴페인-3,레드와인-10'",
        "'제로콜라-10'"
    })
    void 음료만_시킬_경우(String orderMenu) {
        //given, when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> new OrderGroupService(orderMenu));

        //then
        assertEquals(e.getMessage(), "[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
    }


}