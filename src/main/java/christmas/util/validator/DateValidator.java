package christmas.util.validator;

import static christmas.util.Constants.EVENT_MONTH;
import static christmas.util.Constants.EVENT_YEAR;

import java.time.YearMonth;

public class DateValidator {

    public static int validate(String input) {
        validateBlank(input);
        int dateNumber = validateInteger(input);
        return validDateInMonth(dateNumber);
    }

    private static void validateBlank(String input) {
        if(input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 날짜를 필수로 입력해야 합니다. 다시 입력해 주세요.");
        }
    }

    private static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static int validDateInMonth(int date) {
        if(!checkDate(date)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return date;
    }

    private static boolean checkDate(int date) {
        YearMonth eventDate = YearMonth.of(EVENT_YEAR, EVENT_MONTH);
        int lastDate = eventDate.lengthOfMonth();
        return date <= lastDate;
    }
}
