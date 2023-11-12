package christmas;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.Freebie;
import christmas.model.MemberBenefit;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.OrderDate;
import christmas.model.OrderGroup;
import christmas.model.SpecialDiscount;
import christmas.model.WeekdayDiscount;
import christmas.model.WeekendDiscount;
import christmas.model.XmasDiscount;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String inputDate = inputView.askExpectedVisitDate();
        OrderDate date = new OrderDate(Integer.parseInt(inputDate));

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

        // 할인되기 전의 총 가격에 따른 증정품을 가져온다.
        Freebie freebie = Freebie.provideFreebieByPrice(totalAmountBeforeDiscount);
        outputView.printFreebieMenuTitle();
        outputView.printFreebieMenu(freebie.getName());

        //적용되는 할인 조건이 적용가능한지 확인한다.
        List<Discount> appliedBenefit = new ArrayList<>();
        appliedBenefit.add(new XmasDiscount(date, inputOrder));
        appliedBenefit.add(new SpecialDiscount(date, inputOrder));
        appliedBenefit.add(new WeekdayDiscount(date, inputOrder));
        appliedBenefit.add(new WeekendDiscount(date, inputOrder));

        //적용 가능한 조건들을 MemberDiscount에 저장한다.
        MemberBenefit memberBenefit = new MemberBenefit(appliedBenefit, freebie);

        outputView.printBenefitLogTitle();
        outputView.printBenefitDetails(memberBenefit);

        int sum = memberBenefit.getTotalAppliedBenefit();
        outputView.printTotalBenefitPrice(sum);

        int finalAmount = memberBenefit.getTotalAppliedDiscount();
        outputView.printExpectedPayment(totalAmountBeforeDiscount - finalAmount);

        Badge appliedBadge = Arrays.stream(Badge.values())
            .filter(badge -> badge.getMinimumLimit() <= sum)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 뱃지가 없습니다."));
        outputView.printBadge(appliedBadge.getName());
    }
}
