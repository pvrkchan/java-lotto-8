package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Computer {
    private static final int PRICE_PER_LOTTO = 1000;
    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private static final int SCALE = 1;

    public static BigDecimal computeProfit(Lottos lottos, BigDecimal totalPrizeMoney) {
        BigDecimal purchaseAmount = new BigDecimal(PRICE_PER_LOTTO * lottos.getSize());
        totalPrizeMoney = totalPrizeMoney.multiply(HUNDRED);

        RoundingMode roundingMode = RoundingMode.HALF_UP;

        return totalPrizeMoney.divide(purchaseAmount, SCALE, roundingMode);
    }
}
