package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final static String startPlanner = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final static String askVisitDate = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final static String askOrderDetails =
        "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";


    public String askExpectedVisitDate() {
        System.out.printf("%s\n%s", startPlanner, askVisitDate);
        return Console.readLine().trim();
    }

    public String askOrderDetails() {
        System.out.println(askOrderDetails);
        return Console.readLine().trim();
    }
}
