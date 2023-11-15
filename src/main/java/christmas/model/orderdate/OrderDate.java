package christmas.model.orderdate;

import java.util.List;

public class OrderDate {
    private final int date;
    private final String day;

    protected OrderDate(int date, String day) {
        this.date = date;
        this.day = day;
    }

    public boolean isQuailfiedCondition(List<Integer> specialDiscountDays) {
        return specialDiscountDays.contains(date);
    }


    public int getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

}
