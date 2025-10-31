package lotto.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Prizes {
    private final List<Prize> prizes;

    public Prizes(List<Prize> prizes) {
        this.prizes = prizes;
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
