package christmas.model.memberdiscount;

import christmas.model.orderdate.OrderDate;
import christmas.model.ordergroup.OrderGroup;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountFactory {
    private final Set<DiscountService> discountServices = new HashSet<>();

    public static DiscountFactory createDiscountFactory(OrderDate orderDate, OrderGroup orderGroup, int totalPrice) {
        DiscountFactory discountFactory = new DiscountFactory();
        discountFactory.init(orderDate, orderGroup, totalPrice);
        return discountFactory;
    }

    private void init(OrderDate orderDate, OrderGroup orderGroup, int totalPrice) {
        discountServices.add(new XmasDiscountService(orderDate, totalPrice));
        discountServices.add(new SpecialDiscountService(orderDate, totalPrice));
        discountServices.add(new WeekdayDiscountService(orderDate, orderGroup, totalPrice));
        discountServices.add(new WeekendDiscountService(orderDate, orderGroup, totalPrice));
    }

    public List<MemberDiscount> getMemberDiscounts() {
        return discountServices.stream()
            .map(DiscountService::getMemberDiscount)
            .collect(Collectors.toList());
    }
}
