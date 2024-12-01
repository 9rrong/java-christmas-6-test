package christmas.model;

import christmas.utils.OrdersParser;
import java.util.List;
import java.util.stream.Collectors;

public class Orders {

    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders ofValue(String ordersInput) {
        return new Orders(OrdersParser.convertOrders(ordersInput).stream()
                .map(Order::ofValue)
                .collect(Collectors.toUnmodifiableList()));
    }
}
