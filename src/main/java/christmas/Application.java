package christmas;

import static christmas.model.Menu.Category.DESSERT;
import static christmas.model.Menu.Category.MAIN;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.OrderDate;
import christmas.model.OrderGroup;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        outputView.printFreebieMenuTitle();
        outputView.printFreebieMenu(totalAmountBeforeDiscount);

        outputView.printBenefitLogTitle();

        Map<String, Integer> discountList = new HashMap<>();
        List<String> conditions = new ArrayList<>(
            List.of("크리스마스 디데이 할인", "평일 할인", "주말 할인", "특별 할인", "증정 이벤트"));
        List<String> weekdays = new ArrayList<>(
            List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "SUNDAY"));
        List<String> weekends = new ArrayList<>(
            List.of("FRIDAY", "SATURDAY"));
        List<Integer> specialDays = new ArrayList<>(
            List.of(3,10,17,24,25,31));

        for (String condition : conditions) {
            discountList.put(condition, 0);
        }

        if (date.getDate() >= 1 && date.getDate() <= 25) {
            int discountPrice = 1000 + (date.countDiscountDays() * 100);
            discountList.put("크리스마스 디데이 할인", discountPrice);
        }

        if (weekdays.contains(date.getDay())) {
            int discountMenu = 0;
            for (Order order : inputOrder.getOrders()) {
                discountMenu += Menu.countMenu(DESSERT, order.getMenu()) * order.getQuantity();
            }
            discountList.put("평일 할인", discountMenu * 2023);
        }

        if (weekends.contains(date.getDay())) {
            int discountMenu = 0;
            for (Order order : inputOrder.getOrders()) {
                discountMenu += Menu.countMenu(MAIN, order.getMenu()) * order.getQuantity();
            }
            discountList.put("주말 할인", discountMenu * 2023);
        }

        if (specialDays.contains(date.getDate())) {
            discountList.put("특별 할인", 1000);
        }

        if (totalAmountBeforeDiscount >= 120_000) {
            discountList.put("증정 이벤트", 25000);
        }

        int sum = discountList.values().stream()
            .mapToInt(Integer::intValue)
            .sum();

        for (String discountCondition : discountList.keySet()) {
            if(sum == 0) {
                outputView.printNoBenefit();
                break;
            }
            if (discountList.get(discountCondition) == 0) {
                continue;
            }
            outputView.printBenefitDetails(discountCondition, discountList.get(discountCondition));
        }

        outputView.printTotalBenefitPrice(sum);

        if (discountList.get("증정 이벤트") != 0) {
            outputView.printExpectedPayment(totalAmountBeforeDiscount - (sum - 25000));
        } else {
            outputView.printExpectedPayment(totalAmountBeforeDiscount - sum);
        }

        //TODO: 12월 이벤트 배지 - 이벤트 배지가 부여되지 않는 경우, '없음'으로 보여주기


    }
}
