package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String inputDate = inputView.askExpectedVisitDate();
        //TODO: date 변수타입을 하나의 클래스로 만들기
        int date = Integer.parseInt(inputDate);
        //TODO: inputOrder 변수타입을 하나의 클래스로 만들기
        String inputOrder = inputView.askOrderDetails();

        outputView.previewEventBenefits(date);

        System.out.println("\n\n<주문 메뉴>");
        List<Order> orderedMenu = new ArrayList<>();

        for (String food : inputOrder.split(",")) {
            int index = food.indexOf("-");
            String foodName = food.substring(0, index);
            int foodQuantity = Integer.parseInt(food.substring(index + 1));

            orderedMenu.add(new Order(foodName, foodQuantity));

            System.out.printf("%s %d개\n", foodName, foodQuantity);
        }

        int totalPriceBeforeDiscount = 0;
        for(Order order : orderedMenu) {
            totalPriceBeforeDiscount +=
                Menu.getPriceWithFoodNameCondition(order.getMenu()) * order.getQuantity();
        }

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(totalPriceBeforeDiscount);

        Menu freebie = Menu.decideWhetherToGiveFreebie(totalPriceBeforeDiscount);
        System.out.println("\n<증정 메뉴>");
        if(freebie.equals(Menu.FREE_CHAMPAGNE)) {
            System.out.printf("%s %d개\n",freebie.getName(), 1);
        } else {
            System.out.println(freebie.getName());
        }

        System.out.println("\n<혜택 내역>");


    }
}
