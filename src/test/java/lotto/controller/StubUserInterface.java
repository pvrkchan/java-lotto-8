package lotto.controller;

import lotto.domain.number.BonusNumber;
import lotto.ui.UserInterface;

public class StubUserInterface implements UserInterface {

    boolean readMoneyAmountCalled = false;
    boolean readWinningNumbersCalled = false;
    boolean readBonusNumberCalled = false;

    private final int money;
    private final String winningNumbers;
    private final int bonusNumber;

    public StubUserInterface(int money, String winningNumbers, int bonusNumber) {
        this.money = money;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    @Override
    public int readMoneyAmount() {
        readMoneyAmountCalled = true;
        return money;
    }

    @Override
    public String readWinningNumbers() {
        readWinningNumbersCalled = true;
        return winningNumbers;
    }

    @Override
    public int readBonusNumber() {
        readBonusNumberCalled = true;
        return bonusNumber;
    }
}
