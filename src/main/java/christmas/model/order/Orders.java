package christmas.model.order;

import christmas.model.ErrorCode;
import christmas.model.menu.MenuType;
import christmas.utils.OrdersParser;

import java.util.List;

import static christmas.model.menu.MenuType.DRINK;

public class Orders {

    private static final int MENU_MAX_QUANTITY = 20;

    private final List<Order> orders;

    private Orders(List<Order> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    public static Orders ofValue(String ordersInput) {
        return new Orders(OrdersParser.convertOrders(ordersInput).stream()
                .map(Order::ofValue)
                .toList());
    }

    public List<String> getOrderMenus() {
        return orders.stream()
                .map(Order::getOrderMenu)
                .toList();
    }

    public int getPayment() {
        return orders.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public int getQuantityByMenuType(MenuType menuType) {
        return orders.stream()
                .filter(order -> order.isMenuType(menuType))
                .mapToInt(Order::getQuantity)
                .sum();
    }

    private void validateOrders(List<Order> orders) {
        validateQuantity(orders);
        validateOnlyDrink(orders);
    }

    private void validateOnlyDrink(List<Order> orders) {
        if (isOnlyDrink(orders)) {
            throw new IllegalArgumentException(ErrorCode.ORDER_ONLY_DRINK.getMessage());
        }
    }

    private boolean isOnlyDrink(List<Order> orders) {
        return orders.stream()
                .map(Order::getMenuType)
                .distinct()
                .equals(List.of(DRINK));
    }

    private void validateQuantity(List<Order> orders) {
        if (getTotalQuantity(orders) > MENU_MAX_QUANTITY) {
            throw new IllegalArgumentException(ErrorCode.ORDER_QUANTITY_EXCEEDED.getMessage());
        }
    }

    private int getTotalQuantity(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }
}
