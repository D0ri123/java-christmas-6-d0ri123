package christmas.model.freebie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FreebieTest {

    @ParameterizedTest
    @CsvSource(value = {
        "FREE_CHAMPAGNE,125_000",
        "FREE_CHAMPAGNE,120_000",
        "FREE_NONE,119_999",
        "FREE_NONE,0"
    })
    void 최소_조건보다_크면_true를_반환한다(Freebie freebie, int price) {
        boolean expectedResult = freebie.getQualifiedFreebie(price);
        assertTrue(expectedResult);
    }

}