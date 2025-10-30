package lotto.domain;

import static lotto.error.ErrorMessage.MONEY_MINIMUM;
import static lotto.error.ErrorMessage.MONEY_NOT_DIVIDED;

public class Money {
    public static final int PRICE_PER_LOTTO = 1000;
    private int money;

    private Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        validateMinimum(money);
        validateNotDivided(money);
    }

    private void validateMinimum(int money) {
        if (money < PRICE_PER_LOTTO) {
            throw new IllegalArgumentException(MONEY_MINIMUM.formatMessage(PRICE_PER_LOTTO));
        }
    }

    private void validateNotDivided(int money) {
        if (money % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(MONEY_NOT_DIVIDED.formatMessage(PRICE_PER_LOTTO));
        }
    }

    public static Money from(int money) {
        return new Money(money);
    }
}
