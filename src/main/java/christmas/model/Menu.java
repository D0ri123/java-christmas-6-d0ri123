package christmas.model;

import static christmas.model.Menu.Category.APPETIZER;
import static christmas.model.Menu.Category.BEVERAGE;
import static christmas.model.Menu.Category.DESSERT;
import static christmas.model.Menu.Category.MAIN;

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
    CHAMPAGNE(BEVERAGE, "샴페인", 25_000);

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
        BEVERAGE
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public Category getCategory() {
        return category;
    }

    public static int getPriceWithFoodNameCondition(String foodName) {
        return Arrays.stream(Menu.values())
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getPrice)
            .orElseThrow(() -> new IllegalArgumentException("찾는 음식이 없습니다."));
    }

    public static int countMenu(Category category, String foodName) {
        return (int) Arrays.stream(Menu.values())
            .filter(menu -> menu.getName().equals(foodName))
            .filter(menu -> menu.getCategory().equals(category))
            .count();
    }

}
