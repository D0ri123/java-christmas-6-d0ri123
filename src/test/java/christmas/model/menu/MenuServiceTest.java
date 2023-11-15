package christmas.model.menu;

import static org.junit.jupiter.api.Assertions.*;

import christmas.model.menu.Menu.Category;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
        "양송이수프,6_000",
        "바비큐립,54_000",
        "아이스크림,5_000",
        "제로콜라,3_000"
    })
    void 주문한_음식의_가격을_찾는다(String foodName, int foodPrice) {
        //given, when
        int price = MenuService.getPriceByFoodName(foodName);

        //then
        assertEquals(price, foodPrice);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "떡볶이",
        "피자",
        "짜장면",
        "치킨"
    })
    void 주문한_음식이_없는_메뉴라면_예외가_발생한다(String foodName) {
        //given, when
        IllegalArgumentException menuNameException = assertThrows(IllegalArgumentException.class,
            () -> MenuService.getPriceByFoodName(foodName));

        IllegalArgumentException categoryNameException = assertThrows(IllegalArgumentException.class,
            () -> MenuService.getCategoryByFoodName(foodName));

        //then
        assertEquals(menuNameException.getMessage(), "[ERROR] 요청한 메뉴를 찾을 수 없습니다.");
        assertEquals(categoryNameException.getMessage(), "[ERROR] 요청한 메뉴를 찾을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {
        "양송이수프,APPETIZER",
        "바비큐립,MAIN",
        "아이스크림,DESSERT",
        "제로콜라,BEVERAGE"
    })
    void 주문한_음식의_카테고리를_찾는다(String foodName, Category foodCategory) {
        //given, when
        Category expectedCategory = MenuService.getCategoryByFoodName(foodName);

        //then
        assertEquals(foodCategory, expectedCategory);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "양송이수프,APPETIZER,1",
        "바비큐립,MAIN,1",
        "아이스크림,DESSERT,1",
        "제로콜라,BEVERAGE,1",
        "팥빙수,DESSERT,0",
        "하이볼,BEVERAGE,0"
    })
    void 음식이름과_카테고리가_동일한_개수를_센다(String foodName, Category category, int result) {
        //given, when
        int expectedResult = MenuService.countMenuWithCategory(category, foodName);

        //then
        assertEquals(expectedResult, result);
    }
}