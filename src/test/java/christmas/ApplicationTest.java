package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 음료만_주문시_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-15");
            assertThat(output()).contains("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 개수_초과_주문시_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "타파스-19,제로콜라-2");
            assertThat(output()).contains("[ERROR] 총 주문 개수가 20개를 초과했습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 잘못_입력했을_경우_포함() {
        assertSimpleTest(() -> {
            run(
                "32","","3",
                "티본스테이크-1,바비큐립-1,초코케이크-2,초코케이크-1","티본스테이크-10,바비큐립-10,초코케이크-2,제로콜라-1",
                "제로콜라-10","티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.",
                "[ERROR] 날짜를 필수로 입력해야 합니다. 다시 입력해 주세요.",
                "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.",
                "[ERROR] 총 주문 개수가 20개를 초과했습니다. 다시 입력해 주세요.",
                "[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.",
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
