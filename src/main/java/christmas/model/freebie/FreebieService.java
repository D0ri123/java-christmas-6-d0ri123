package christmas.model.freebie;

import static christmas.model.freebie.Freebie.*;

import java.util.List;

public class FreebieService {
    private static final List<Freebie> freebies = List.of(values());

    public static Freebie getFreebieByPreDiscountPrice(int price) {
        return freebies.stream()
            .filter(freebie -> price >= freebie.getMinimumLimit())
            .findFirst()
            .orElse(FREE_NONE);
    }
}
