package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String VISIT_DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_AND_QUANTITY_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public String askVisitDate() {
        System.out.println(VISIT_DATE_PROMPT);
        return Console.readLine();
    }

    public String askMenuAndQuantity() {
        System.out.println(MENU_AND_QUANTITY_PROMPT);
        return Console.readLine();
    }
}
