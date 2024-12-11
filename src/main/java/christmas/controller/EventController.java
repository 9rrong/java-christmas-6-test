package christmas.controller;

import christmas.model.Receipt;
import christmas.model.VisitDate;
import christmas.model.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Receipt receipt;

    public EventController(InputView inputView, OutputView outputView, Receipt receipt) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.receipt = receipt;
    }

    public void run() {
        outputView.printGreeting();

        VisitDate visitDate = retryUntilValid(() -> VisitDate.ofValue(inputView.askVisitDate()));
        Orders orders = retryUntilValid(() -> Orders.ofValue(inputView.askMenuAndQuantity()));
        outputView.printReceipt(receipt.produceReceipt(orders, visitDate.toLocalDate()), visitDate.getValue());

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
