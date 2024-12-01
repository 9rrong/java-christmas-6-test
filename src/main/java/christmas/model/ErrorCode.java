package christmas.model;

public enum ErrorCode {
    VISIT_DATE_NOT_NUMBER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    VISIT_DATE_NOT_IN_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR]";

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
