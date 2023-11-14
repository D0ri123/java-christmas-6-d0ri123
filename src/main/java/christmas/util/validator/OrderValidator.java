package christmas.util.validator;

import christmas.model.menu.Menu;

public class OrderValidator {

    public static void validate(String input, int quantity) {
        validateExistMenu(input);
        validateMenuQuantity(quantity);
    }

    public static void validateExistMenu(String input) {
        Menu.getAllMenus().stream()
            .filter(menu -> menu.getName().equals(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    public static void validateMenuQuantity(int quantity) {
        if(quantity < 1 || quantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
