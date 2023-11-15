package christmas.model.orderdate;

import java.util.List;

public class OrderDate {
    private final int date;
    private final String day;

    protected OrderDate(int date, String day) {
        this.date = date;
        this.day = day;
    }

    public boolean isQualifiedSpecialCondition(List<Integer> specialDiscountDays) {
        return specialDiscountDays.contains(date);
    }

    public boolean isQualifiedWeekCondition(List<String> condition) {
        return condition.contains(day);
    }

    public boolean isQualifiedXmasCondition(int start, int end) {
        return date >= start && date <= end;
    }

    public int calculateDay() {
        return date - 1;
    }

    public int getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }
}
