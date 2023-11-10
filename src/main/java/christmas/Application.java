package christmas;

import christmas.view.InputView;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        String inputDate = inputView.askExpectedVisitDate();
        int date = Integer.parseInt(inputDate);
        String inputOrder = inputView.askOrderDetails();

        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);

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
