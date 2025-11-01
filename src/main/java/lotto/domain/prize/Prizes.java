package lotto.domain.prize;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static lotto.error.ErrorMessage.PRIZES_NULL;

public class Prizes {
    private final List<Prize> prizes;

    public Prizes(List<Prize> prizes) {
        validateNull(prizes);
        this.prizes = prizes;
    }

    private void validateNull(List<Prize> prizes) {
        if (prizes == null) {
            System.out.println(PRIZES_NULL.message());
            throw new IllegalArgumentException(PRIZES_NULL.message());
        }
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public int getFrequency(Prize prize) {
        return Collections.frequency(prizes, prize);
    }

    public BigDecimal getTotalPrizeMoney() {
        return prizes.stream()
                .map(Prize::getPrizeMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
