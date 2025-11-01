package lotto.domain.prize;

import java.math.BigDecimal;

public enum Prize {
    THREE(BigDecimal.valueOf(5_000)),
    FOUR(BigDecimal.valueOf(50_000)),
    FIVE(BigDecimal.valueOf(1_500_000)),
    FIVE_BONUS(BigDecimal.valueOf(30_000_000)),
    SIX(BigDecimal.valueOf(2_000_000_000));

    private final BigDecimal prizeMoney;

    Prize(BigDecimal prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public BigDecimal getPrizeMoney() {
        return prizeMoney;
    }
}
