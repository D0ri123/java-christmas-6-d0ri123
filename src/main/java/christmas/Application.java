package christmas;

import christmas.model.date.OrderDateService;
import christmas.model.ordergroup.OrderGroupService;
import christmas.model.badge.BadgeService;
import christmas.model.service.discount.DiscountFactory;
import christmas.model.freebie.Freebie;
import christmas.model.domain.MemberDiscount;
import christmas.model.freebie.FreebieService;
import christmas.model.benefit.MemberBenefit;
import christmas.model.benefit.MemberBenefitService;
import christmas.model.menu.MenuService;
import christmas.model.order.Order;
import christmas.model.date.OrderDate;
import christmas.model.ordergroup.OrderGroup;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String inputDate = inputView.askExpectedVisitDate();
        OrderDateService orderDateService = new OrderDateService(inputDate);
        OrderDate date = orderDateService.getOrderDate();

        //주문 메뉴를 입력받아 OrderGroup에 메뉴와 개수를 저장한다.
        String orderMenu = inputView.askOrderDetails();
        OrderGroupService orderGroupService = new OrderGroupService(orderMenu);
        MenuService menuService = new MenuService();
        OrderGroup inputOrder = orderGroupService.getOrders();

        outputView.previewEventBenefits(date.getDate());

        outputView.printOrderMenuTitle();
        for (Order order : inputOrder.getOrders()) {
            outputView.printOrderSummary(order.getMenu(), order.getQuantity());
        }

        //할인되기 전의 총 가격을 구한다.
        outputView.printTotalAmountBeforeDiscount();
        int totalAmountBeforeDiscount = 0;
        for (Order order : inputOrder.getOrders()) {
            totalAmountBeforeDiscount += menuService.getPriceWithFoodNameCondition(order.getMenu()) * order.getQuantity();
        }
        outputView.printTotalAmountFormatting(totalAmountBeforeDiscount);

        // 할인되기 전의 총 가격에 따른 증정품을 가져온다.
        FreebieService freebieService = new FreebieService(Freebie.getAllFreebies());
        Freebie freebie = freebieService.provideFreebieByPrice(totalAmountBeforeDiscount);
        outputView.printFreebieMenuTitle();
        outputView.printFreebieMenu(freebie.getName());

        DiscountFactory.init(date, inputOrder, totalAmountBeforeDiscount);
        List<MemberDiscount> appliedDiscounts = DiscountFactory.getMemberDiscounts();

        //적용 가능한 조건들을 MemberDiscount에 저장한다.
        MemberBenefit memberBenefit = new MemberBenefit(appliedDiscounts, freebie);
        MemberBenefitService memberBenefitService = new MemberBenefitService(memberBenefit);

        outputView.printBenefitLogTitle();
        outputView.printBenefitDetails(memberBenefit.getAppliedDiscount(), memberBenefitService.getFreebieOrNull());

        int sum = memberBenefitService.getTotalAppliedBenefit();
        outputView.printTotalBenefitPrice(sum);

        int finalAmount = memberBenefitService.getTotalAppliedDiscount();
        outputView.printExpectedPayment(totalAmountBeforeDiscount - finalAmount);

        BadgeService badgeService = new BadgeService();
        outputView.printBadge(badgeService.getBadgeByTotalPrice(sum));
    }
}
