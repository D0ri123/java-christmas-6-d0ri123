package christmas.model.domain;

import static christmas.util.Constants.*;

import christmas.util.validator.DateValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrderDate {
    private final int date;
    private final String day;

    public OrderDate(String date) {
        this.date = DateValidator.validate(date);
        this.day = getDayOfWeekFromDate(this.date);
    }

    private String getDayOfWeekFromDate(int date) {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), EVENT_MONTH, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public int getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

}
