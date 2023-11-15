package christmas.view;

import static christmas.util.Constants.EVENT_MONTH;

import christmas.model.freebie.Freebie;
import christmas.model.memberdiscount.MemberDiscount;
import christmas.model.order.Order;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final String eventBenefits = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String orderMenuTitle = "\n\n<주문 메뉴>";
    private static final String orderSummary = "%s %d개\n";
    private static final String totalAmountBeforeDiscountTitle = "\n<할인 전 총주문 금액>";
    private static final String moneyUnit = "%s원";
    private static final String freebieMenuTitle = "\n\n<증정 메뉴>";
    private static final String benefitLogTitle = "\n<혜택 내역>";
    private static final String benefitDetails = "%s: -%s원\n";
    private static final String noBenefit = "없음";
    private static final String totalBenefitPriceTitle = "\n<총혜택 금액>";
    private static final String expectedPaymentTitle = "\n\n<할인 후 예상 결제 금액>";
    private static final String badgeTitle = "\n\n<%d월 이벤트 배지>\n";

    public void previewEventBenefits(int date) {
        System.out.printf(eventBenefits, EVENT_MONTH, date);
    }

    public void printOrderMenuTitle() {
        System.out.println(orderMenuTitle);
    }

    public void printOrderSummary(List<Order> orders) {
        for(Order order : orders) {
            System.out.printf(orderSummary, order.getMenu(), order.getQuantity());
        }
    }

    public void printTotalAmountBeforeDiscount() {
        System.out.println(totalAmountBeforeDiscountTitle);
    }

    public void printTotalAmountFormatting(int totalAmountBeforeDiscount) {
        String totalAmount = formattingMoney(totalAmountBeforeDiscount);
        System.out.printf(moneyUnit, totalAmount);
    }

    public void printFreebieMenuTitle() {
        System.out.println(freebieMenuTitle);
    }

    public void printFreebieMenu(String freebieName) {
        System.out.println(freebieName);
    }

    private String formattingMoney(int totalAmount) {
        DecimalFormat money = new DecimalFormat("#,###");
        return money.format(totalAmount);
    }

    public void printBenefitLogTitle() {
        System.out.println(benefitLogTitle);
    }

    public void printBenefitDetails(String discountCondition, int discountPrice) {
        System.out.printf(benefitDetails, discountCondition, formattingMoney(discountPrice));
    }

    public void printNoBenefit() {
        System.out.println(noBenefit);
    }

    public void printTotalBenefitPrice(int sum) {
        System.out.println(totalBenefitPriceTitle);
        if(sum == 0) {
            System.out.printf(moneyUnit, formattingMoney(sum));
            return;
        }
        System.out.printf(moneyUnit, formattingMoney(-sum));
    }

    public void printExpectedPayment(int paymentAmount) {
        System.out.println(expectedPaymentTitle);
        System.out.printf(moneyUnit, formattingMoney(paymentAmount));
    }

    public void printBadge(String name) {
        System.out.printf(badgeTitle, EVENT_MONTH);
        System.out.println(name);
    }

    public void printBenefitDetails(List<MemberDiscount> discountServiceEvent, Freebie freebie) {
        if(discountServiceEvent.size() == 0) {
            printNoBenefit();
        }
        for (MemberDiscount memberDiscount : discountServiceEvent) {
            printBenefitDetails(memberDiscount.getEventName(), memberDiscount.getAppliedPrice());
        }
        if (freebie != Freebie.FREE_NONE) {
            printBenefitDetails("증정 이벤트", freebie.getPrice());
        }
    }
}
