package christmas.model.freebie;

import static christmas.model.freebie.Freebie.*;

import java.util.List;

public class FreebieService {
    private static final List<Freebie> freebies = List.of(values());

    public static Freebie getFreebieByPreDiscountPrice(int price) {
        return freebies.stream()
            .filter(freebie -> freebie.getQualifiedFreebie(price))
            .findFirst()
            .orElse(FREE_NONE);
    }
}
