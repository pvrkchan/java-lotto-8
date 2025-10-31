package lotto.domain;

import java.util.List;

import static lotto.error.ErrorMessage.BONUS_NUMBER_DUPLICATE;

public class BonusNumber {
    private final Number bonusNumber;

    public BonusNumber(Number bonusNumber, WinningNumbers winningNumbers) {
        validate(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validate(Number bonusNumber, WinningNumbers winningNumbers) {
        List<Integer> numbers = winningNumbers.getWinningNumbers();
        if(numbers.contains(bonusNumber.getNumber())){
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE.message());
        }
    }

    public int getBonusNumber() {
        return bonusNumber.getNumber();
    }
}
