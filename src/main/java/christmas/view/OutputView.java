package christmas.view;

import static christmas.util.Constants.EVENT_MONTH;

import christmas.model.freebie.Freebie;
import christmas.model.memberdiscount.MemberDiscount;
import christmas.model.order.Order;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final String EVENT_PREVIEW_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_TITLE = "\n\n<주문 메뉴>";
    private static final String ORDER_SUMMARY = "%s %d개\n";
    private static final String ORIGINAL_TOTAL_PRICE_TITLE = "\n<할인 전 총주문 금액>";
    private static final String MONEY_UNIT = "%s원";
    private static final String FREEBIE_MENU_TITLE = "\n\n<증정 메뉴>";
    private static final String BENEFIT_LOG_TITLE = "\n<혜택 내역>";
    private static final String BENEFIT_LOG = "%s: -%s원\n";
    private static final String TOTAL_BENEFIT_PRICE_TITLE = "\n<총혜택 금액>";
    private static final String DISCOUNTED_PAYMENT_TITLE = "\n\n<할인 후 예상 결제 금액>";
    private static final String MONTHLY_EVENT_BADGE_HEADER = "\n\n<%d월 이벤트 배지>\n";

    public void previewEventBenefits(int date) {
        System.out.printf(EVENT_PREVIEW_MESSAGE, EVENT_MONTH, date);
    }

    public void printOrderSummary(List<Order> orders) {
        System.out.println(ORDER_MENU_TITLE);
        for(Order order : orders) {
            System.out.printf(ORDER_SUMMARY, order.getMenu(), order.getQuantity());
        }
    }

    public void printTotalAmountFormatting(int totalAmountBeforeDiscount) {
        System.out.println(ORIGINAL_TOTAL_PRICE_TITLE);
        String totalAmount = formattingMoney(totalAmountBeforeDiscount);
        System.out.printf(MONEY_UNIT, totalAmount);
    }

    public void printFreebieMenu(String freebieName) {
        System.out.println(FREEBIE_MENU_TITLE);
        System.out.println(freebieName);
    }

    private String formattingMoney(int totalAmount) {
        DecimalFormat money = new DecimalFormat("#,###");
        return money.format(totalAmount);
    }

    public void printBenefitLogTitle() {
        System.out.println(BENEFIT_LOG_TITLE);
    }

    public void printBenefitDetails(String discountCondition, int discountPrice) {
        System.out.printf(BENEFIT_LOG, discountCondition, formattingMoney(discountPrice));
    }

    public void printTotalBenefitPrice(int sum) {
        System.out.println(TOTAL_BENEFIT_PRICE_TITLE);
        if(sum == 0) {
            System.out.printf(MONEY_UNIT, formattingMoney(sum));
            return;
        }
        System.out.printf(MONEY_UNIT, formattingMoney(-sum));
    }

    public void printExpectedPayment(int paymentAmount) {
        System.out.println(DISCOUNTED_PAYMENT_TITLE);
        System.out.printf(MONEY_UNIT, formattingMoney(paymentAmount));
    }

    public void printBadge(String name) {
        System.out.printf(MONTHLY_EVENT_BADGE_HEADER, EVENT_MONTH);
        System.out.println(name);
    }

    public void printBenefitDetails(List<MemberDiscount> discountServiceEvent, Freebie freebie) {
        if(discountServiceEvent.size() == 0) {
            System.out.println(freebie.getName());
            return;
        }
        for (MemberDiscount memberDiscount : discountServiceEvent) {
            printBenefitDetails(memberDiscount.getEventName(), memberDiscount.getAppliedPrice());
        }
        if (freebie != Freebie.FREE_NONE) {
            printBenefitDetails("증정 이벤트", freebie.getPrice());
        }
    }
}
