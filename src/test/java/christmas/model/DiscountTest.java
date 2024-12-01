package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DiscountTest {

    @Test
    void 크리스마스_디데이_할인_적용() {
        LocalDate date = LocalDate.of(2023, 12, 24);
        assertThat(Discount.CHRISTMAS_D_DAY.getDiscount(null, date)).isEqualTo(3300);
    }
}