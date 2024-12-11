package christmas.view;

import java.util.List;

public class OutputView {
    private static final String GREETING_ANNOUNCE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_DESCRIPTION_HEADER_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printError(String message) {
        System.out.println(message);
    }

    public void printGreeting() {
        System.out.println(GREETING_ANNOUNCE);
    }

    public void printReceipt(List<String> receiptMessage, int visitDate) {
        System.out.println(String.format(EVENT_DESCRIPTION_HEADER_FORMAT, visitDate));
        receiptMessage.forEach(System.out::println);
    }
}
