package christmas.model.service;

import christmas.model.domain.Menu;
import java.util.List;

public class MenuService {
    private final List<Menu> menus;

    public MenuService(List<Menu> menus) {
        this.menus = menus;
    }

    public int getPriceWithFoodNameCondition(String foodName) {
        return menus.stream()
            .filter(menu -> menu.getName().equals(foodName))
            .findFirst()
            .map(Menu::getPrice)
            .orElseThrow(() -> new IllegalArgumentException("찾는 음식이 없습니다."));
    }

}
