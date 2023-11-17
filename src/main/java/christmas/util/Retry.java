package christmas.util;

import java.util.function.Supplier;

public class Retry {
    public static <T> T retryOnException(Supplier<T> operation) {
        while(true) {
            try {
                return operation.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
