package christmas;

import static christmas.Menu.Category.APPETIZER;
import static christmas.Menu.Category.BEVERAGE;
import static christmas.Menu.Category.DESSERT;
import static christmas.Menu.Category.FREE;
import static christmas.Menu.Category.MAIN;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),
    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BBQ_RIB(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    X_MAS_PASTA(MAIN, "크리스마스파스타", 25_000),
    CHOCO_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),
    ZERO_COKE(BEVERAGE, "제로콜라", 3_000),
    RED_WINE(BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000),
    FREE_CHAMPAGNE(FREE, "샴페인", -25_000),
    FREE_NONE(FREE, "없음", 0);

    private final Category category;
    private final String name;
    private final int price;

    Menu(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }



    public enum Category {
        APPETIZER,
        MAIN,
        DESSERT,
        BEVERAGE,
        FREE
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static int getPriceWithFoodNameCondition(String foodName) {
        return Arrays.stream(Menu.values())
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getPrice)
            .orElseThrow(() -> new IllegalArgumentException("찾는 음식이 없습니다."));
    }

    public static Menu decideWhetherToGiveFreebie(int totalPriceBeforeDiscount) {
        if (totalPriceBeforeDiscount >= 120_000) {
            return FREE_CHAMPAGNE;
        }
        return FREE_NONE;
    }
}
