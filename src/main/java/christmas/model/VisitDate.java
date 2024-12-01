package christmas.model;

public class VisitDate {

    private final int value;

    private VisitDate(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static VisitDate ofValue(String visitDate) {
        int value = convertToInt(visitDate);
        validateInRange(value);

        return new VisitDate(value);
    }

    private static int convertToInt(String visitDate) {
        try {
            return Integer.parseInt(visitDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.VISIT_DATE_NOT_NUMBER.getMessage());
        }
    }

    private static void validateInRange(int value) {
        if (value < 1 || value > 31) {
            throw new IllegalArgumentException(ErrorCode.VISIT_DATE_NOT_IN_RANGE.getMessage());
        }
    }
}
