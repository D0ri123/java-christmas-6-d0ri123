package christmas;

public class Order {

    private final String menu;
    private final int quantity;

    public Order(String menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public String getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
