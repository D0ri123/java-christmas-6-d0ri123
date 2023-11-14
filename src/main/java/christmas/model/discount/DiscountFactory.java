package christmas.model.discount;

import christmas.model.date.OrderDate;
import christmas.model.ordergroup.OrderGroup;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountFactory {
    private static final Set<DiscountService> discountServices = new HashSet<>();

    public static void init(OrderDate orderDate, OrderGroup orderGroup, int totalPrice) {
        discountServices.add(new XmasDiscountService(orderDate, totalPrice));
        discountServices.add(new SpecialDiscountService(orderDate, totalPrice));
        discountServices.add(new WeekdayDiscountService(orderDate, orderGroup, totalPrice));
        discountServices.add(new WeekendDiscountService(orderDate, orderGroup, totalPrice));
    }

    public static List<MemberDiscount> getMemberDiscounts() {
        return discountServices.stream()
            .map(DiscountService::getMemberDiscount)
            .collect(Collectors.toList());
    }
}
