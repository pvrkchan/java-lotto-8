package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.error.ErrorMessage.OUT_OF_COUNT;
import static lotto.error.ErrorMessage.WINNING_NUMBER_DUPLICATE;

public class WinningNumbers {
    private static final int MAXIMUM_COUNT = 6;

    private List<Number> winningNumbers;

    public WinningNumbers(List<Number> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<Number> winningNumbers) {
        validateSize(winningNumbers);
        validateDuplicate(winningNumbers);
    }

    private void validateSize(List<Number> winningNumbers) {
        if (winningNumbers.size() != MAXIMUM_COUNT){
            throw new IllegalArgumentException(OUT_OF_COUNT.formatMessage(MAXIMUM_COUNT));
        }
    }

    private void validateDuplicate(List<Number> winningNumbers) {
        Set<Number> numbers = new HashSet<>(winningNumbers);
        if (numbers.size() != winningNumbers.size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE.message());
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.stream()
                .map(Number::getNumber)
                .toList();
    }
}
