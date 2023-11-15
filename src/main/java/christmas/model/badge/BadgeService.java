package christmas.model.badge;

import java.util.List;

public class BadgeService {

    private static final List<Badge> badges = List.of(Badge.values());

    public static String getBadgeByTotalPrice(int totalPrice) {
        return badges.stream()
            .filter(badge -> badge.getMinimumLimit() <= totalPrice)
            .findFirst()
            .map(Badge::getName)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 뱃지가 없습니다."));
    }
}
