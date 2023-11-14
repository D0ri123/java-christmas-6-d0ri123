package christmas.util.validator;

import christmas.model.order.Order;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderGroupValidator {

    public static void validateDuplicateMenu(List<Order> orders) {
        Set<Order> orderSet = new HashSet<>(orders);
        if (orders.size() != orderSet.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateInputTemplate(String input) {
        Pattern template = Pattern.compile("([가-힣]+-[0-9]+,)*[가-힣]+-[0-9]+");
        Matcher matcher = template.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateMaxOrderQuantity(List<Order> orders) {
        int totalQuantity = orders.stream()
            .mapToInt(Order::getQuantity)
            .sum();
        if(totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 총 주문 개수가 20개를 초과했습니다. 다시 입력해 주세요.");
        }
    }

    //TODO: 음료만 포함하는지 확인한다.
    public static void validateOnlyBeverage(List<Order> orders) {

    }

}
