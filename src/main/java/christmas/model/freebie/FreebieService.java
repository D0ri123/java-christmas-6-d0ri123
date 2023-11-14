package christmas.model.freebie;

import static christmas.model.freebie.Freebie.*;

import java.util.List;

public class FreebieService {
    private static final List<Freebie> freebies = getAllFreebies();

    public static String provideFreebieByPrice(int price) {
        return freebies.stream()
            .filter(freebie -> price >= freebie.getMinimumLimit())
            .findFirst()
            .map(Freebie::getName)
            .orElse(FREE_NONE.getName());
    }

    public static Freebie getFreebieByPrice(int price) {
        return freebies.stream()
            .filter(freebie -> price >= freebie.getMinimumLimit())
            .findFirst()
            .orElse(FREE_NONE);
    }
}
