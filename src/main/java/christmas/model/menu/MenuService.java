package christmas.model.menu;

import christmas.model.menu.Menu.Category;
import java.util.List;

public class MenuService {
    private static final List<Menu> menus = List.of(Menu.values());

    public static int getPriceWithFoodNameCondition(String foodName) {
        return menus.stream()
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getPrice)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 요청한 메뉴를 찾을 수 없습니다."));
    }

    public static Category findCategory(String foodName) {
        return menus.stream()
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getCategory)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 요청한 메뉴를 찾을 수 없습니다."));
    }

    public static int getMenuWithCategory(Category category, String foodName) {
        return (int) menus.stream()
            .filter(menu -> menu.getName().equals(foodName))
            .filter(menu -> menu.getCategory().equals(category))
            .count();
    }
}
