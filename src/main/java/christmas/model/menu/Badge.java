package christmas.model.menu;

import java.util.Arrays;

public enum Badge {
    NONE("없음", 0, 5_000),
    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Integer.MAX_VALUE);

    private final String name;
    private final int minPrice;
    private final int maxPrice;

    Badge(String name, int minPrice, int maxPrice) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getName() {
        return name;
    }

    public static Badge from(int price) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.isPrice(price))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isPrice(int price) {
        return minPrice <= Math.abs(price) && Math.abs(price) < maxPrice;
    }
}
