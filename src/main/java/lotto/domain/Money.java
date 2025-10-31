package lotto.domain;

import static lotto.error.ErrorMessage.MONEY_MINIMUM;
import static lotto.error.ErrorMessage.MONEY_NOT_DIVIDED;

public class Money {
    private static final int PRICE_PER_LOTTO = 1000;
    private static final int MINIMUM_BALANCE = 0;
    private int balance;

    private Money(int money) {
        validateBalance(money);
        this.balance = money;
    }

    private void validateBalance(int money) {
        if(money < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(MONEY_MINIMUM.formatMessage(PRICE_PER_LOTTO));
        }
    }

    public static Money from(final int money) {
        validateNotDivided(money);
        return new Money(money);
    }

    private static void validateNotDivided(int money) {
        if (money % PRICE_PER_LOTTO != 0) {
            throw new IllegalArgumentException(MONEY_NOT_DIVIDED.formatMessage(PRICE_PER_LOTTO));
        }
    }

    public Money pay(){
        return new Money(balance - PRICE_PER_LOTTO);
    }

    public boolean isRunOutOf() {
        return balance == MINIMUM_BALANCE;
    }
}
