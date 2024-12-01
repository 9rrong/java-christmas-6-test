package christmas.model.order;

import christmas.dto.OrderDTO;
import christmas.model.ErrorCode;
import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;

public class Order {

    public static final int QUANTITY_MIN_VALUE = 0;
    private final Menu menu;
    private final int quantity;

    private Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order ofValue(OrderDTO orderDTO) {
        String menuName = orderDTO.menuName();
        int quantity = orderDTO.quantity();
        validateQuantityInRange(quantity);

        return new Order(
                Menu.findByMenuName(menuName),
                quantity
        );
    }

    public boolean isMenuType(MenuType menuType) {
        return menu.getMenuType().equals(menuType);
    }

    public int getQuantity() {
        return quantity;
    }

    private static void validateQuantityInRange(int quantity) {
        if (quantity < QUANTITY_MIN_VALUE) {
            throw new IllegalArgumentException(ErrorCode.MENU_QUANTITY_NOT_IN_RANGE.getMessage());
        }
    }
}
