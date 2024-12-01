package christmas.utils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.dto.OrderDTO;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-1", "해산물파스타-2"})
    void 주문_올바른_입력_출력테스트(String ordersInput) {
        if (ordersInput.equals("타파스-1,제로콜라-1")) {
            List<OrderDTO> expectedOrders = List.of(
                    new OrderDTO("타파스", 1),
                    new OrderDTO("제로콜라", 1)
            );
            List<OrderDTO> actualOrders = OrdersParser.convertOrders(ordersInput);
            assertThat(actualOrders).isEqualTo(expectedOrders);

        }

        if (ordersInput.equals("해산물파스타-2")) {
            List<OrderDTO> expectedOrders = List.of(
                    new OrderDTO("해산물파스타", 2)
            );
            List<OrderDTO> actualOrders = OrdersParser.convertOrders(ordersInput);
            assertThat(actualOrders).isEqualTo(expectedOrders);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-2", "가-가", "가-2,", "가-2,3-3"})
    void 주문_잘못된_입력_예외테스트(String input) {
        assertThrows(IllegalArgumentException.class, () -> OrdersParser.convertOrders(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 주문_공백_입력_예외테스트(String input) {
        assertThrows(IllegalArgumentException.class, () -> OrdersParser.convertOrders(input));
    }
}