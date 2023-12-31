package christmas;

import christmas.controller.EventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        EventController eventPlanner = new EventController(inputView, outputView);
        eventPlanner.progressReservation();
    }
}
