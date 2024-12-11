package christmas;

import christmas.controller.EventController;
import christmas.model.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Receipt receipt = new Receipt();

        EventController eventController = new EventController(inputView, outputView, receipt);

        eventController.run();
    }
}
