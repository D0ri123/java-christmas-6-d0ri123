package christmas.model.badge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "25_000,산타",
        "20_000,산타",
        "19_900,트리",
        "15_000,트리",
        "10_000,트리",
        "9_900,별",
        "5_000,별",
        "3_500,없음",
        "0,없음"
    })
    void 할인받는_가격에_따라_배지를_다르게_받는다(int price, String badge) {
        String badgeByPrice = BadgeService.getBadgeByTotalBenefitPrice(price);
        assertEquals(badge, badgeByPrice);
    }

}