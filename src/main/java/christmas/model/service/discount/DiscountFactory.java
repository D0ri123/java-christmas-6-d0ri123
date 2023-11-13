package christmas.model.service.discount;

import christmas.model.domain.MemberDiscount;
import christmas.model.domain.OrderDate;
import christmas.model.domain.OrderGroup;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountFactory {
    private static final Set<DiscountService> discountServices = new HashSet<>();

    public static void init(OrderDate orderDate, OrderGroup orderGroup) {
        discountServices.add(new XmasDiscountService(orderDate));
        discountServices.add(new SpecialDiscountService(orderDate));
        discountServices.add(new WeekdayDiscountService(orderDate, orderGroup));
        discountServices.add(new WeekendDiscountService(orderDate, orderGroup));
    }

    public static List<MemberDiscount> getMemberDiscounts() {
        return discountServices.stream()
            .map(DiscountService::getMemberDiscount)
            .collect(Collectors.toList());
    }
}
