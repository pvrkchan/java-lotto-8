package lotto.error;

import lotto.domain.Money;

public enum ErrorMessage {
    MONEY_AMOUNT_NOT_NUMBER("[ERROR] 금액으로는 숫자만 입력 가능합니다."),
    MONEY_NOT_DIVIDED("[ERROR] 로또 구입 금액은 %d원 단위로 입력해야 합니다."),
    MONEY_MINIMUM("[ERROR] 로또 구입 금액은 %d원 이상 입력해야 합니다.");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }

    public String message() {
        return message;
    }
}
