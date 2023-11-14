package christmas.model.badge;

import java.util.List;

public class BadgeService {
    private final List<Badge> badges = Badge.getAllBadges();

    public String getBadgeByTotalPrice(int totalPrice) {
        return badges.stream()
            .filter(badge -> badge.getMinimumLimit() <= totalPrice)
            .findFirst()
            .map(Badge::getName)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 뱃지가 없습니다."));
    }
}
