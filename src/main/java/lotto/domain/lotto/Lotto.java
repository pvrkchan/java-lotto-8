package lotto.domain.lotto;

import lotto.domain.number.WinningInformation;
import lotto.domain.prize.Prize;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static lotto.error.ErrorMessage.LOTTO_NUMBER_DUPLICATE;
import static lotto.error.ErrorMessage.LOTTO_NUMBER_SIZE;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != numbers.size()) {
            System.out.println(LOTTO_NUMBER_DUPLICATE);
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATE.message());
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            System.out.println(LOTTO_NUMBER_SIZE.formatMessage(LOTTO_NUMBER_COUNT));
            throw new IllegalArgumentException(LOTTO_NUMBER_SIZE.formatMessage(LOTTO_NUMBER_COUNT));
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public Optional<Prize> checkWinning(WinningInformation winningInformation) {
        long matchCount = numbers.stream()
                .filter(winningInformation.getWinningNumbers()::contains)
                .count();
        if (matchCount == 3)
            return Optional.of(Prize.THREE);
        if (matchCount == 4)
            return Optional.of(Prize.FOUR);
        if (matchCount == 5)
            return Optional.ofNullable(checkBonusNumber(winningInformation.getBonusNumbers()));
        if (matchCount == 6)
            return Optional.of(Prize.SIX);
        return Optional.empty();
    }

    public Prize checkBonusNumber(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            return Prize.FIVE_BONUS;
        }
        return Prize.FIVE;
    }
}
