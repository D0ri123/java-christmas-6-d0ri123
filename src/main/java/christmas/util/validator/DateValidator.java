package christmas.util.validator;

import static christmas.util.Constants.EVENT_MONTH;
import static christmas.util.Constants.EVENT_YEAR;
import static christmas.util.ExceptionMessage.DATE_REQUIRED_MESSAGE;
import static christmas.util.ExceptionMessage.INVALID_DATE_MESSAGE;

import java.time.YearMonth;

public class DateValidator {

    public static int validate(String input) {
        validateBlank(input);
        int dateNumber = validateInteger(input);
        return validDateInMonth(dateNumber);
    }

    private static void validateBlank(String input) {
        if(input.isBlank()) {
            throw new IllegalArgumentException(DATE_REQUIRED_MESSAGE);
        }
    }

    private static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(INVALID_DATE_MESSAGE);
        }
    }

    private static int validDateInMonth(int date) {
        if(!checkDate(date)) {
            throw new IllegalArgumentException(INVALID_DATE_MESSAGE);
        }
        return date;
    }

    private static boolean checkDate(int date) {
        YearMonth eventDate = YearMonth.of(EVENT_YEAR, EVENT_MONTH);
        int lastDate = eventDate.lengthOfMonth();
        return date <= lastDate;
    }
}
