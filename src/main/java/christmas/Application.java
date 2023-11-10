package christmas;


import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int day = Integer.parseInt(Console.readLine());

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String menu = Console.readLine().trim();
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", day);

        System.out.println("\n\n<주문 메뉴>");
        List<Order> orderedMenu = new ArrayList<>();

        for (String food : menu.split(",")) {
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
