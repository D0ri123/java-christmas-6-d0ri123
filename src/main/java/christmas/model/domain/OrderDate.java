package christmas.model.domain;

public class OrderDate {
    private final int date;
    private final String day;

    public OrderDate(int date, String day) {
        this.date = date;
        this.day = day;
    }

    public int getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

}
