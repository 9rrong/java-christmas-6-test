package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @ParameterizedTest
    @CsvSource({"1,1", "10,10", "31,31"})
    void 방문날짜_올바른_입력_출력테스트(String visitDate, int expected) {
        assertThat(VisitDate.ofValue(visitDate).getValue()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "40", "a", "-10"})
    void 방문날짜_잘못된_입력_예외테스트(String visitDate) {
        assertThrows(IllegalArgumentException.class, () -> VisitDate.ofValue(visitDate));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 방문날짜_공백_입력_예외테스트(String visitDate) {
        assertThrows(IllegalArgumentException.class, () -> VisitDate.ofValue(visitDate));
    }
}