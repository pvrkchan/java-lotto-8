package lotto.error;

public enum ErrorMessage {
    LOTTO_NUMBER_DUPLICATE("[ERROR] 로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_SIZE("[ERROR] 로또 번호는 %d개여야 합니다."),
    LOTTOS_EMPTY("[ERROR] 로또 목록은 비어있을 수 없습니다."),
    MONEY_AMOUNT_NOT_NUMBER("[ERROR] 금액으로는 숫자만 입력 가능합니다."),
    MONEY_NOT_DIVIDED("[ERROR] 로또 구입 금액은 %d원 단위로 입력해야 합니다."),
    MONEY_MINIMUM("[ERROR] 로또 구입 금액은 %d원 이상 입력해야 합니다."),
    SEPERATE_NOT_NUMBER("[ERROR] 당첨번호 입력은 숫자만 가능합니다."),
    END_WITH_SEPERATOR("[ERROR] 구분자가 잘못 입력되었습니다."),
    EMPTY_WINNING_NUMBERS("[ERROR] 당첨번호는 비어있을 수 없습니다."),
    OUT_OF_NUMBER_RANGE("[ERROR] 당첨번호 입력은 %d부터 %d까지만 가능합니다."),
    OUT_OF_COUNT("[ERROR] 당첨번호는 %d개를 입력해야 합니다."),
    WINNING_NUMBER_DUPLICATE("[ERROR] 당첨번호는 중복될 수 없습니다."),
    BONUS_NOT_NUMBER("[ERROR] 보너스번호 입력은 숫자만 가능합니다."),
    BONUS_NUMBER_DUPLICATE("[ERROR] 입력하신 보너스번호가 당첨번호와 중복되었습니다."),
    PRIZES_NULL("[ERROR] 상 목록이 NULL 입니다.");

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
