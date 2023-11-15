package christmas.util.validator;

import christmas.model.menu.Menu;
import java.util.Arrays;

public class OrderValidator {

//    public static void validateTemplate(String[] splitMenu) {
//        if(splitMenu.length != 2) {
//            throw new IllegalArgumentException("[")
//        }
//    }

    public static void validate(String input, int quantity) {
        validateExistMenu(input);
        validateMenuQuantity(quantity);
    }

    private static void validateExistMenu(String input) {
        Arrays.stream(Menu.values())
            .filter(menu -> menu.getName().equals(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    private static void validateMenuQuantity(int quantity) {
        if(quantity < 1 || quantity > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
