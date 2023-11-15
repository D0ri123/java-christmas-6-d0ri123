package christmas.model.badge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @ParameterizedTest
    @CsvSource(value = {
        "SANTA,20_000",
        "SANTA,21_000",
        "TREE,19_999",
        "TREE,15_000",
        "STAR,7_000",
        "STAR,5_000",
        "NOTHING,4_900"
    })
    void 최소_조건보다_크면_true를_반환한다(Badge badge, int price) {
        boolean expectedResult = badge.getQualifiedBadge(price);
        assertTrue(expectedResult);
    }
}