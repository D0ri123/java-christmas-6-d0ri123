package christmas.model.freebie;

import java.util.List;

public class FreebieService {
    private final List<Freebie> freebies;

    public FreebieService(List<Freebie> freebies) {
        this.freebies = freebies;
    }

    public Freebie provideFreebieByPrice(int price) {
        return freebies.stream()
            .filter(freebie -> price >= freebie.getMinimumLimit())
            .findFirst()
            .orElse(Freebie.FREE_NONE);
    }
}
