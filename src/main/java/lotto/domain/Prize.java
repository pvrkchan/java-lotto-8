package lotto.domain;

import java.math.BigInteger;

public enum Prize {
    THREE(BigInteger.valueOf(5_000)),
    FOUR(BigInteger.valueOf(50_000)),
    FIVE(BigInteger.valueOf(1_500_000)),
    FIVE_BONUS(BigInteger.valueOf(30_000_000)),
    SIX(BigInteger.valueOf(2_000_000_000));

    private final BigInteger prizeMoney;

    Prize(BigInteger prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public BigInteger getPrizeMoney() {
        return prizeMoney;
    }
}
