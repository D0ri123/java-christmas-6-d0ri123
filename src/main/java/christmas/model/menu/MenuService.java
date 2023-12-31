package christmas.model.menu;

import christmas.model.menu.Menu.Category;
import java.util.List;

public class MenuService {
    private static final List<Menu> menus = List.of(Menu.values());

    public static int getPriceByFoodName(String foodName) {
        return menus.stream()
            .filter(menu -> menu.isSameFoodName(foodName))
            .findFirst()
            .map(Menu::getPrice)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 요청한 메뉴를 찾을 수 없습니다."));
    }

    public static Category getCategoryByFoodName(String foodName) {
        return menus.stream()
            .filter(menu -> menu.isSameFoodName(foodName))
            .findFirst()
            .map(Menu::getCategory)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 요청한 메뉴를 찾을 수 없습니다."));
    }

    public static int countMenuWithCategory(Category category, String foodName) {
        return (int) menus.stream()
            .filter(menu -> menu.isSameFoodName(foodName))
            .filter(menu -> menu.isSameFoodCategory(category))
            .count();
    }

}
