package christmas.model;

import java.util.Arrays;
import java.util.List;

public enum Freebie {
    FREE_CHAMPAGNE("샴페인 1개", 25_000, 120_000),
    FREE_NONE("없음", 0, 0);

    private final String name;
    private final int price;
    private final int minimumLimit;

    Freebie(String name, int price, int minimumLimit) {
        this.name = name;
        this.price = price;
        this.minimumLimit = minimumLimit;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getMinimumLimit() {
        return minimumLimit;
    }

    public static List<Freebie> getAllFreebies() {
        return Arrays.stream(Freebie.values()).toList();
    }

}
