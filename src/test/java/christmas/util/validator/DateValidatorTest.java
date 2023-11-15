package christmas.util.validator;

import static christmas.util.ExceptionMessage.DATE_REQUIRED_MESSAGE;
import static christmas.util.ExceptionMessage.INVALID_DATE_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateValidatorTest {

    @Test
    void 값을_입력하지_않으면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> DateValidator.validate(""));
        assertEquals(e.getMessage(), DATE_REQUIRED_MESSAGE);
    }

    @Test
    void 숫자가_아닌_문자를_입력하면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> DateValidator.validate("qwe"));
        assertEquals(e.getMessage(), INVALID_DATE_MESSAGE);
    }

    @Test
    void 이벤트월에_해당하지_않는_날짜를_입력하면_예외발생() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
            () -> DateValidator.validate("32"));
        assertEquals(e.getMessage(), INVALID_DATE_MESSAGE);
    }
}