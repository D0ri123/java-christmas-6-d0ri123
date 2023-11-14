package christmas.util.validator;

import christmas.model.domain.Menu.Category;
import christmas.model.order.Order;
import christmas.model.service.MenuService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderGroupValidator {

    public static void validateInputTemplate(String input) {
        Pattern template = Pattern.compile("([가-힣]+-[0-9]+,)*[가-힣]+-[0-9]+");
        Matcher matcher = template.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrderGroup(List<Order> orders) {
        validateDuplicateMenu(orders);
        validateMaxOrderQuantity(orders);
        validateOnlyBeverage(orders);
    }

    private static void validateDuplicateMenu(List<Order> orders) {
        Set<Order> orderSet = new HashSet<>(orders);
        if (orders.size() != orderSet.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateMaxOrderQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
            .mapToInt(Order::getQuantity)
            .sum();
        if(totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 총 주문 개수가 20개를 초과했습니다. 다시 입력해 주세요.");
        }
    }

    private static void validateOnlyBeverage(List<Order> orders) {
        Set<Category> categories = orders.stream()
            .map(order -> MenuService.findCategory(order.getMenu()))
            .collect(Collectors.toSet());

        if (categories.size() == 1 && categories.contains(Category.BEVERAGE)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }

}
