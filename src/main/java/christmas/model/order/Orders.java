package christmas.model.order;

import christmas.model.menu.MenuType;
import christmas.utils.OrdersParser;

import java.util.List;

public class Orders {

    private final List<Order> orders;

    private Orders(List<Order> orders) {
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
}
