package christmas.model.orderdate;

public class OrderDate {
    private final int date;
    private final String day;

    protected OrderDate(int date, String day) {
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
