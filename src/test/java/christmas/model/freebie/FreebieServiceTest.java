package christmas.model.freebie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FreebieServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "150_000,샴페인 1개",
        "120_000,샴페인 1개",
        "119_900,없음",
        "0,없음"
    })
    void 주문_가격에_따라_증정품을_다르게_받는다1(int price, String freebie) {
        String expectedFreebie = FreebieService.provideFreebieByPrice(price);
        assertEquals(freebie, expectedFreebie);
    }

    //TODO: 이게 과연 필요할까? 두 메서드가 중복적이지 않나? 생각해야 할 필요가 있다.
    @ParameterizedTest
    @CsvSource(value = {
        "150_000,FREE_CHAMPAGNE",
        "120_000,FREE_CHAMPAGNE",
        "119_900,FREE_NONE",
        "0,FREE_NONE"
    })
    void 주문_가격에_따라_증정품_다르게_받는다2(int price, Freebie freebie) {
        Freebie expectedFreebie = FreebieService.getFreebieByPrice(price);
        assertEquals(freebie, expectedFreebie);
    }

}