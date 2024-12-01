package christmas.model;

import java.util.Arrays;

public enum Menu {
    양송이수프(MenuType.APPETIZER, 6000),
    타파스(MenuType.APPETIZER, 5500),
    시저샐러드(MenuType.APPETIZER, 8000),
    티본스테이크(MenuType.MAIN, 55000),
    바비큐립(MenuType.MAIN, 54000),
    해산물파스타(MenuType.MAIN, 35000),
    크리스마스파스타(MenuType.MAIN, 25000),
    초코케이크(MenuType.DESSERT, 15000),
    아이스크림(MenuType.DESSERT, 5000),
    제로콜라(MenuType.DRINK, 3000),
    레드와인(MenuType.DRINK, 60000),
    샴페인(MenuType.DRINK, 25000);

    private final MenuType menuType;
    private final int price;

    Menu(MenuType menuType, int price) {
        this.menuType = menuType;
        this.price = price;
    }

    public static Menu findByMenuName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.MENU_NOT_FOUND.getMessage()));
    }
}
