package christmas;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.OrderDate;
import christmas.model.OrderGroup;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        // 주문 날짜를 입력받아 OrderDate에 날짜와 요일을 저장한다.
        String inputDate = inputView.askExpectedVisitDate();
        OrderDate date = new OrderDate(Integer.parseInt(inputDate));

        //TODO: OrderDate 만을 가지고 알 수 있는 할인 정보 ->

        //주문 메뉴를 입력받아 OrderGroup에 메뉴와 개수를 저장한다.
        OrderGroup inputOrder = new OrderGroup(inputView.askOrderDetails());

        outputView.previewEventBenefits(date.getDate());

        outputView.printOrderMenuTitle();
        for (Order order : inputOrder.getOrders()) {
            outputView.printOrderSummary(order.getMenu(), order.getQuantity());
        }

        //할인되기 전의 총 가격을 구한다.
        outputView.printTotalAmountBeforeDiscount();
        int totalAmountBeforeDiscount = 0;
        for (Order order : inputOrder.getOrders()) {
            totalAmountBeforeDiscount += Menu.getPriceWithFoodNameCondition(order.getMenu()) * order.getQuantity();
        }
        outputView.printTotalAmountFormatting(totalAmountBeforeDiscount);

        //TODO: 증정 메뉴 - 할인 전 총주문 금액이 12만원 이상일 때, 샴페인 1개 증정
        outputView.printFreebieMenuTitle();
        outputView.printFreebieMenu(totalAmountBeforeDiscount);

        //TODO: 혜택 내역 - 고객에게 적용된 이벤트 내역. 출력 순서는 자유롭게


        //TODO: 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격


        //TODO: 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액


        //TODO: 12월 이벤트 배지 - 이벤트 배지가 부여되지 않는 경우, '없음'으로 보여주기

    }
}
