package christmas.model;

import christmas.model.menu.MenuType;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static christmas.model.Receipt.CHAMPAGNE_GIFT_TARGET_PRICE;
import static christmas.model.Receipt.WON_FORMAT;

public enum Discount {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", (orders, date) -> {
        if (isInChristmasRange(date)) {
            return -((date.getDayOfMonth() - 1) * 100 + 1000);
        }
        return 0;
    }),
    WEEKDAY("평일 할인", (orders, date) -> {
        if (isWeekday(date)) {
            return -orders.getQuantityByMenuType(MenuType.DESSERT) * 2023;
        }
        return 0;
    }),
    WEEKEND("주말 할인", (orders, date) -> {
        if (isWeekend(date)) {
            return -orders.getQuantityByMenuType(MenuType.MAIN) * 2023;
        }
        return 0;
    }),
    SPECIAL("특별 할인", (orders, date) -> {
        if (isSpecial(date)) {
            return -1000;
        }
        return 0;
    }),
    GIFT("증정 이벤트", (orders, date) -> {
        if (isGift(orders)) {
            return -25000;
        }
        return 0;
    });

    private final String discountName;
    private final BiFunction<Orders, LocalDate, Integer> discountExpression;

    Discount(String discountName, BiFunction<Orders, LocalDate, Integer> discountExpression) {
        this.discountName = discountName;
        this.discountExpression = discountExpression;
    }

    public static List<String> getDiscountDetail(Orders orders, LocalDate date) {
        List<Discount> values = new ArrayList<>(List.of(Discount.values()));

        if (isWeekday(date)) {
            values.remove(WEEKEND);
        }
        if (isWeekend(date)) {
            values.remove(WEEKDAY);
        }

        return values.stream()
                .map(discount -> discount.getDiscountName() + ": " + String.format(WON_FORMAT, discount.discountExpression.apply(orders, date)))
                .toList();
    }

    public static int getTotalBenefit(Orders orders, LocalDate date) {
        return Arrays.stream(values())
                .mapToInt(discount -> discount.discountExpression.apply(orders, date))
                .sum();
    }

    public static int getTotalBenefitWithoutGift(Orders orders, LocalDate date) {
        return Arrays.stream(values())
                .filter(discount -> discount != GIFT)
                .mapToInt(discount -> discount.discountExpression.apply(orders, date))
                .sum();
    }

    private static boolean isInChristmasRange(LocalDate date) {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);

        return !date.isAfter(endDate) && !date.isBefore(startDate);
    }

    private static boolean isWeekday(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        List<Integer> weekday = Arrays.asList(1, 2, 3, 4, 7);
        return weekday.contains(dayOfWeek);
    }

    private static boolean isWeekend(LocalDate date) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        List<Integer> weekend = Arrays.asList(5, 6);
        return weekend.contains(dayOfWeek);
    }

    private static boolean isSpecial(LocalDate date) {
        int dayOfWeek = date.getDayOfMonth();
        List<Integer> specialDays = Arrays.asList(3, 10, 17, 24, 25, 31);
        return specialDays.contains(dayOfWeek);
    }

    private static boolean isGift(Orders orders) {
        return orders.getPayment() > CHAMPAGNE_GIFT_TARGET_PRICE;
    }

    public String getDiscountName() {
        return discountName;
    }
}
