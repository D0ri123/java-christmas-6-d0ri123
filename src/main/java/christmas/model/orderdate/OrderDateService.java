package christmas.model.orderdate;

import static christmas.util.Constants.EVENT_MONTH;

import christmas.util.validator.DateValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrderDateService {

    private final OrderDate orderDate;

    public OrderDateService(String input) {
        this.orderDate = generageOrderDate(input);
    }

    private OrderDate generageOrderDate(String input) {
        int date = DateValidator.validate(input);
        String day = getDayOfWeekFromDate(date);
        return new OrderDate(date, day);
    }

    private String getDayOfWeekFromDate(int date) {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), EVENT_MONTH, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public OrderDate getOrderDate() {
        return orderDate;
    }

    public int getVisitDate() {
        return orderDate.getDate();
    }

}
