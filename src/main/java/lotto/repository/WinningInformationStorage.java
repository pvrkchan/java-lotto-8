package lotto.repository;

import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningInformation;
import lotto.domain.number.WinningNumbers;

import java.util.List;

public class WinningInformationStorage implements WinningInformation {
    private WinningNumbers winningNumbers;
    private BonusNumber bonusNumber;


    @Override
    public void save(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    @Override
    public List<Integer> getWinningNumbers() {
        return winningNumbers.getWinningNumbers();
    }

    @Override
    public int getBonusNumber() {
        return bonusNumber.getBonusNumber();
    }
}
