package lotto.domain.number;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.error.ErrorMessage.*;

public class WinningNumbers {
    private static final int MAXIMUM_COUNT = 6;

    private List<Number> winningNumbers;

    public WinningNumbers(List<Number> winningNumbers) {
        validate(winningNumbers);
        this.winningNumbers = winningNumbers;
    }

    private void validate(List<Number> winningNumbers) {
        validateNullOrEmpty(winningNumbers);
        validateSize(winningNumbers);
        validateDuplicate(winningNumbers);
    }

    private void validateNullOrEmpty(List<Number> winningNumbers) {
        if (winningNumbers == null || winningNumbers.isEmpty()) {
            System.out.println(EMPTY_WINNING_NUMBERS.message());
            throw new IllegalArgumentException(EMPTY_WINNING_NUMBERS.message());
        }
    }

    private void validateSize(List<Number> winningNumbers) {
        if (winningNumbers.size() != MAXIMUM_COUNT) {
            System.out.println(OUT_OF_COUNT.formatMessage(MAXIMUM_COUNT));
            throw new IllegalArgumentException(OUT_OF_COUNT.formatMessage(MAXIMUM_COUNT));
        }
    }

    private void validateDuplicate(List<Number> winningNumbers) {
        Set<Number> numbers = new HashSet<>(winningNumbers);
        if (numbers.size() != winningNumbers.size()) {
            System.out.println(WINNING_NUMBER_DUPLICATE.message());
            throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE.message());
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.stream()
                .map(Number::getNumber)
                .toList();
    }
}
