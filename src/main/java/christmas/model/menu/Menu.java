package christmas.model.menu;

import christmas.model.ErrorCode;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", MenuType.APPETIZER, 6000),
    TAPAS("타파스", MenuType.APPETIZER, 5500),
    CAESAR_SALAD("시저샐러드", MenuType.APPETIZER, 8000),
    T_BONE_STEAK("티본스테이크", MenuType.MAIN, 55000),
    BARBEQUE_RIB("바비큐립", MenuType.MAIN, 54000),
    SEAFOOD_PASTA("해산물파스타", MenuType.MAIN, 35000),
    CHRISTMAS_PASTA("크리스마스파스타", MenuType.MAIN, 25000),
    CHOCOLATE_CAKE("초코케이크", MenuType.DESSERT, 15000),
    ICE_CREAM("아이스크림", MenuType.DESSERT, 5000),
    ZERO_COKE("제로콜라", MenuType.DRINK, 3000),
    RED_WINE("레드와인", MenuType.DRINK, 60000),
    CHAMPAGNE("샴페인", MenuType.DRINK, 25000);

    private final String menuName;
    private final MenuType menuType;
    private final int price;

    Menu(String menuName, MenuType menuType, int price) {
        this.menuName = menuName;
        this.menuType = menuType;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public int getPrice() {
        return price;
    }

    public static Menu findByMenuName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.menuName.equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.MENU_NOT_FOUND.getMessage()));
    }
}
