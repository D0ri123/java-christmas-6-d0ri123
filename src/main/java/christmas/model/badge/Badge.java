package christmas.model.badge;

import java.util.Arrays;
import java.util.List;

public enum Badge {
    SANTA(20_000, "산타"),
    TREE(10_000, "트리"),
    STAR(5_000, "별"),
    NOTHING(0, "없음");

    private final int minimumLimit;
    private final String name;

    Badge(int minimumLimit, String name) {
        this.minimumLimit = minimumLimit;
        this.name = name;
    }

    public static List<Badge> getAllBadges() {
        return Arrays.stream(Badge.values()).toList();
    }

    public String getName() {
        return name;
    }

    public int getMinimumLimit() {
        return minimumLimit;
    }
}
