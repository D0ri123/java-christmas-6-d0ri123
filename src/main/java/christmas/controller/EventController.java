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

    private void start() {
        inputView.start();
    }

    private void getVisitDate() {
        orderDateService = Retry.retryOnException(() -> {
            String inputDate = inputView.askExpectedVisitDate();
            return new OrderDateService(inputDate);
        });
    }

    private void orderMenu() {
        orderGroupService = Retry.retryOnException(() -> {
            String menu = inputView.askOrderDetails();
            return new OrderGroupService(menu);
        });
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
