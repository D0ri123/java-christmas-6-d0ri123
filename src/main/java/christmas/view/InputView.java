package christmas.view;

import static christmas.util.Constants.EVENT_MONTH;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String EVENT_PLANNER_GREETING = "안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n";
    private final static String VISIT_DATE_PROMPT = "%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n";
    private final static String ORDER_MENU_PROMPT =
        "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";


    public void start() {
        System.out.printf(EVENT_PLANNER_GREETING, EVENT_MONTH);
    }

    public String askExpectedVisitDate() {
        System.out.printf(VISIT_DATE_PROMPT, EVENT_MONTH);
        return Console.readLine().trim();
    }

    public String askOrderDetails() {
        System.out.println(ORDER_MENU_PROMPT);
        return Console.readLine().trim();
    }
}
