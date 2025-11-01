package lotto.view;

import lotto.domain.lotto.Lottos;
import lotto.domain.prize.Prizes;

import java.math.BigDecimal;

public interface OutputView {

    void printBuyingResult(Lottos lottos);

    void printPrizeResult(Prizes prizes, BigDecimal profitPercent);
}
