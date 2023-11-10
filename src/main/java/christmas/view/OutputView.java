package christmas.view;

import static christmas.util.Constants.EVENT_MONTH;

import java.text.DecimalFormat;

public class OutputView {
    private static final String eventBenefits = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String orderMenuTitle = "\n\n<주문 메뉴>";
    private static final String orderSummary = "%s %d개\n";
    private static final String totalAmountBeforeDiscountTitle = "<할인 전 총주문 금액>";
    private static final String moneyUnit = "%s원";

    public void previewEventBenefits(int date) {
        System.out.printf(eventBenefits, EVENT_MONTH, date);
    }

    public void printOrderMenuTitle() {
        System.out.println(orderMenuTitle);
    }

    public void printOrderSummary(String menu, int quantity) {
        System.out.printf(orderSummary, menu, quantity);
    }

    public void printTotalAmountBeforeDiscount() {
        System.out.println(totalAmountBeforeDiscountTitle);
    }

    public void printTotalAmountFormatting(int totalAmountBeforeDiscount) {
        DecimalFormat money = new DecimalFormat("#,###");
        String totalAmount = money.format(totalAmountBeforeDiscount);
        System.out.printf(moneyUnit, totalAmount);
    }
}
