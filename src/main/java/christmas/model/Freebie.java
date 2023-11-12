package christmas.model;

import java.util.Arrays;

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

    public static Freebie provideFreebieByPrice(int price) {
        return Arrays.stream(Freebie.values())
            .filter(freebie -> price >= freebie.getMinimumLimit())
            .findFirst()
            .orElse(Freebie.FREE_NONE);
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

}
