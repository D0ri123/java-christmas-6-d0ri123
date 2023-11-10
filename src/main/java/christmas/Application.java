package christmas;

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

        //주문 메뉴를 입력받아 OrderGroup에 메뉴와 개수를 저장한다.
        OrderGroup inputOrder = new OrderGroup(inputView.askOrderDetails());

        outputView.previewEventBenefits(date.getDate());

        outputView.printOrderMenuTitle();
        for(Order order : inputOrder.getOrders()) {
            outputView.printOrderSummary(order.getMenu(), order.getQuantity());
        }

    }
}
