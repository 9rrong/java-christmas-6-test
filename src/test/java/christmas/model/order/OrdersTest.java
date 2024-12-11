package christmas.model.order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OrdersTest {

    @ParameterizedTest
    @ValueSource(strings = {"[제로콜라-1]", "[레드와인-2]"})
    void 음료만_주문_예외테스트(String input) {
        assertThrows(IllegalArgumentException.class, () -> Orders.ofValue(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"[제로콜라-21]", "[레드와인-22]"})
    void 최대개수_초과_주문_예외테스트(String input) {
        assertThrows(IllegalArgumentException.class, () -> Orders.ofValue(input));
    }
}