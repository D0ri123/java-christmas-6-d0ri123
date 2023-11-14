package christmas.controller;

import christmas.model.badge.BadgeService;
import christmas.model.benefit.MemberBenefitService;
import christmas.model.date.OrderDateService;
import christmas.model.discount.DiscountFactory;
import christmas.model.freebie.FreebieService;
import christmas.model.ordergroup.OrderGroupService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private OrderDateService orderDateService;
    private OrderGroupService orderGroupService;
    private MemberBenefitService memberBenefitService;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void progressReservation() {
        getVisitDate();
        orderMenu();
        previewBenefits();
        previewOrderedMenu();
        previewPriceBeforeBenefit();
        previewFreebie();
        previewBenefitLog();
        previewTotalBenefitPrice();
        previewExpectedPayment();
        previewBadge();
    }

    private void getVisitDate() {
        String inputDate = inputView.askExpectedVisitDate();
        orderDateService = new OrderDateService(inputDate);
    }

    private void orderMenu() {
        String orderMenu = inputView.askOrderDetails();
        orderGroupService = new OrderGroupService(orderMenu);
    }

    private void previewBenefits() {
        outputView.previewEventBenefits(orderDateService.getVisitDate());
    }

    private void previewOrderedMenu() {
        outputView.printOrderMenuTitle();
        outputView.printOrderSummary(orderGroupService.getOrderList());
    }

    private void previewPriceBeforeBenefit() {
        outputView.printTotalAmountBeforeDiscount();
        outputView.printTotalAmountFormatting(orderGroupService.calculateTotalPrice());
    }

    private void previewFreebie() {
        int totalPrice = orderGroupService.calculateTotalPrice();
        String freebieName = FreebieService.provideFreebieByPrice(totalPrice);

        outputView.printFreebieMenuTitle();
        outputView.printFreebieMenu(freebieName);
    }

    private void previewBenefitLog() {
        DiscountFactory.init(
            orderDateService.getOrderDate(), orderGroupService.getOrders(), orderGroupService.calculateTotalPrice()
        );
        memberBenefitService = new MemberBenefitService(
            DiscountFactory.getMemberDiscounts(), FreebieService.getFreebieByPrice(orderGroupService.calculateTotalPrice()));

        outputView.printBenefitLogTitle();
        outputView.printBenefitDetails(memberBenefitService.getMemberDiscountServices(), memberBenefitService.getFreebieOrNull());
    }

    private void previewTotalBenefitPrice() {
        int totalAppliedBenefit = memberBenefitService.getTotalAppliedBenefit();
        outputView.printTotalBenefitPrice(totalAppliedBenefit);
    }

    private void previewExpectedPayment() {
        int totalAppliedDiscount = memberBenefitService.getTotalAppliedDiscount();
        int totalPrice = orderGroupService.calculateTotalPrice();
        outputView.printExpectedPayment(totalPrice - totalAppliedDiscount);
    }

    private void previewBadge() {
        int sum = memberBenefitService.getTotalAppliedBenefit();
        outputView.printBadge(BadgeService.getBadgeByTotalPrice(sum));
    }

}