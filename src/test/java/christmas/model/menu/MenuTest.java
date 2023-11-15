package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu.Category;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {

    @ParameterizedTest
    @CsvSource(value = {
        "MUSHROOM_SOUP,양송이수프,true",
        "MUSHROOM_SOUP,크림수프,false",
        "BBQ_RIB,바비큐립,true"
    })
    void 찾으려는_음식이름과_동일한_메뉴가_있으면_true를_반환한다(Menu menu, String foodName, boolean isMatched) {
        boolean expectedResult = menu.isSameFoodName(foodName);
        assertEquals(expectedResult, isMatched);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "MUSHROOM_SOUP,APPETIZER,true",
        "X_MAS_PASTA,APPETIZER,false",
        "BBQ_RIB,MAIN,true"
    })
    void 찾으려는_카테고리의_메뉴가_있으면_true를_반환한다(Menu menu, Category foodCategory, boolean isMatched) {
        boolean expectedResult = menu.isSameFoodCategory(foodCategory);
        assertEquals(expectedResult, isMatched);
    }

}