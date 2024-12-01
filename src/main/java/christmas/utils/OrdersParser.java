package christmas.utils;

import christmas.dto.OrderDTO;
import christmas.model.ErrorCode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrdersParser {
    private static final String ORDERS_REGEX = "([가-힣]+-[0-9]+)(,[가-힣]+-[0-9]+)*";
    private static final Pattern ORDERS_PATTERN = Pattern.compile(ORDERS_REGEX);
    private static final String ORDERS_DELIMITER = ",";
    private static final String ORDER_DELIMITER = "-";
    private static final int ORDER_NAME_INDEX = 0;
    private static final int ORDER_QUANTITY_INDEX = 1;

    public static List<OrderDTO> convertOrders(String orderInput) {
        validateOrderInput(orderInput);
        List<OrderDTO> orderDTOs = parseOrders(orderInput);
        validateDuplicatedNames(orderDTOs);

        return orderDTOs;
    }

    private static List<OrderDTO> parseOrders(String orderInput) {
        return Arrays.stream(orderInput.split(ORDERS_DELIMITER))
                .map(order -> {
                    String[] parts = order.split(ORDER_DELIMITER);
                    String name = parts[ORDER_NAME_INDEX].trim();
                    int quantity = Integer.parseInt(parts[ORDER_QUANTITY_INDEX].trim());

                    return new OrderDTO(name, quantity);
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private static void validateDuplicatedNames(List<OrderDTO> orderDTOs) {
        Set<String> uniqueNames = new HashSet<>();

        for (OrderDTO orderDTO : orderDTOs){
            if(!uniqueNames.add(orderDTO.menuName())){
                throw new IllegalArgumentException(ErrorCode.ORDER_MENU_DUPLICATED.getMessage());
            }
        }
    }

    private static void validateOrderInput(String orderInput) {
        validateNullOrBlank(orderInput);
        validateOrderSyntax(orderInput);
    }

    private static void validateOrderSyntax(String orderInput) {
        Matcher matcher = ORDERS_PATTERN.matcher(orderInput);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.ORDER_ILLEGAL_SYNTAX.getMessage());
        }
    }

    private static void validateNullOrBlank(String orderInput) {
        if (orderInput == null || orderInput.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.ORDER_ILLEGAL_SYNTAX.getMessage());
        }
    }
}
