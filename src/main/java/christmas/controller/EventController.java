package christmas.controller;

import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGreeting();

        VisitDate visitDate = retryUntilValid(() -> VisitDate.ofValue(inputView.askVisitDate()));

    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
