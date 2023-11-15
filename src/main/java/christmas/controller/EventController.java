package christmas.controller;

import christmas.model.badge.BadgeService;
import christmas.model.memberbenefit.MemberBenefitService;
import christmas.model.orderdate.OrderDateService;
import christmas.model.memberdiscount.DiscountFactory;
import christmas.model.freebie.FreebieService;
import christmas.model.ordergroup.OrderGroupService;
import christmas.util.Retry;
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
        start();
        requestInput();
        showOrderDetails();
        showBenefitDetails();
    }

    private void start() {
        inputView.start();
    }

    private void requestInput() {
        getVisitDateFromMember();
        getMenuFromMember();
    }

    private void showOrderDetails() {
        previewEventBenefitsOnDate();
        listAllMenus();
        showPreDiscountOrderPrice();
    }

    private void showBenefitDetails() {
        showFreebieItem();
        showMemberBenefits();
        showBenefitAmount();
        showDiscountedPrice();
        showMemberBadge();
    }

    private void getVisitDateFromMember() {
        orderDateService = Retry.retryOnException(() -> {
            String inputDate = inputView.askExpectedVisitDate();
            return new OrderDateService(inputDate);
        });
    }

    private void getMenuFromMember() {
        orderGroupService = Retry.retryOnException(() -> {
            String menu = inputView.askOrderDetails();
            return new OrderGroupService(menu);
        });
    }

    private void previewEventBenefitsOnDate() {
        outputView.previewEventBenefits(orderDateService.getVisitDate());
    }

    private void listAllMenus() {
        outputView.printOrderMenuTitle();
        outputView.printOrderSummary(orderGroupService.getOrderList());
    }

    private void showPreDiscountOrderPrice() {
        outputView.printTotalAmountBeforeDiscount();
        outputView.printTotalAmountFormatting(orderGroupService.calculateTotalPrice());
    }

    private void showFreebieItem() {
        int totalPrice = orderGroupService.calculateTotalPrice();
        String freebieName = FreebieService.provideFreebieByPrice(totalPrice);

        outputView.printFreebieMenuTitle();
        outputView.printFreebieMenu(freebieName);
    }

    private void showMemberBenefits() {
        DiscountFactory discountFactory = DiscountFactory.createDiscountFactory(
            orderDateService.getOrderDate(), orderGroupService.getOrders(), orderGroupService.calculateTotalPrice()
        );
        memberBenefitService = new MemberBenefitService(
            discountFactory.getMemberDiscounts(), FreebieService.getFreebieByPrice(orderGroupService.calculateTotalPrice()));

        outputView.printBenefitLogTitle();
        outputView.printBenefitDetails(memberBenefitService.getMemberDiscountServices(), memberBenefitService.getFreebieOrNull());
    }

    private void showBenefitAmount() {
        int totalAppliedBenefit = memberBenefitService.getTotalAppliedBenefit();
        outputView.printTotalBenefitPrice(totalAppliedBenefit);
    }

    private void showDiscountedPrice() {
        int totalAppliedDiscount = memberBenefitService.getTotalAppliedDiscount();
        int totalPrice = orderGroupService.calculateTotalPrice();
        outputView.printExpectedPayment(totalPrice - totalAppliedDiscount);
    }

    private void showMemberBadge() {
        int sum = memberBenefitService.getTotalAppliedBenefit();
        outputView.printBadge(BadgeService.getBadgeByTotalPrice(sum));
    }

}
