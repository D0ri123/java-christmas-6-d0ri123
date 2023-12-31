package christmas.util.validator;

import static christmas.util.Constants.VALID_MAXIMUM;
import static christmas.util.Constants.VALID_MINIMUM;
import static christmas.util.ExceptionMessage.INVALID_ORDER_MESSAGE;

import christmas.model.menu.Menu;
import java.util.Arrays;

public class OrderValidator {

    public static void validate(String input, int quantity) {
        validateExistMenu(input);
        validateMenuQuantity(quantity);
    }

    private static void validateExistMenu(String input) {
        Arrays.stream(Menu.values())
            .filter(menu -> menu.isSameFoodName(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER_MESSAGE));
    }

    private static void validateMenuQuantity(int quantity) {
        if(quantity < VALID_MINIMUM || quantity > VALID_MAXIMUM) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }
}
