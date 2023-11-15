package christmas.util.validator;

import static christmas.util.ExceptionMessage.INVALID_ORDER_MESSAGE;
import static christmas.util.ExceptionMessage.MAXIMUM_OVER_MESSAGE;
import static christmas.util.ExceptionMessage.NOT_ALLOWED_ONLY_BEVERAGE;

import christmas.model.menu.Menu.Category;
import christmas.model.order.Order;
import christmas.model.menu.MenuService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderGroupValidator {
    private static final String regex = "([가-힣]+-[0-9]+,)*[가-힣]+-[0-9]+";
    private static final int VALID_MAXIMUM = 20;

    public static void validateInputTemplate(String input) {
        Pattern template = Pattern.compile(regex);
        Matcher matcher = template.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    public static void validateOrderGroup(List<Order> orders) {
        validateDuplicateMenu(orders);
        validateMaxOrderQuantity(orders);
        validateOnlyBeverage(orders);
    }

    private static void validateDuplicateMenu(List<Order> orders) {
        Set<Order> distinctOrders = new HashSet<>(orders);
        if (orders.size() != distinctOrders.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_MESSAGE);
        }
    }

    private static void validateMaxOrderQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
            .mapToInt(Order::getQuantity)
            .sum();
        if(totalQuantity > VALID_MAXIMUM) {
            throw new IllegalArgumentException(MAXIMUM_OVER_MESSAGE);
        }
    }

    private static void validateOnlyBeverage(List<Order> orders) {
        Set<Category> categories = orders.stream()
            .map(order -> MenuService.getCategoryByFoodName(order.getMenu()))
            .collect(Collectors.toSet());

        if (categories.size() == 1 && categories.contains(Category.BEVERAGE)) {
            throw new IllegalArgumentException(NOT_ALLOWED_ONLY_BEVERAGE);
        }
    }

}
