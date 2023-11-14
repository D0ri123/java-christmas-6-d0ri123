package christmas.model.service;

import christmas.model.domain.Menu;
import christmas.model.domain.Menu.Category;
import java.util.List;

public class MenuService {
    private static final List<Menu> menus = Menu.getAllMenus();

    public int getPriceWithFoodNameCondition(String foodName) {
        return menus.stream()
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getPrice)
            .orElseThrow(() -> new IllegalArgumentException("찾는 음식이 없습니다."));
    }

    public static Category findCategory(String foodName) {
        return menus.stream()
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getCategory)
            .orElseThrow(() -> new IllegalArgumentException("찾는 음식이 없습니다."));
    }

}
