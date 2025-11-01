package lotto.controller;

import lotto.domain.lotto.Lottos;
import lotto.domain.prize.Prizes;
import lotto.view.OutputView;

import java.math.BigDecimal;

public class SpyOutputView implements OutputView {

    Lottos lottos;
    Prizes prizes;
    BigDecimal profitPercent;

    @Override
    public void printBuyingResult(Lottos lottos) {
        this.lottos = lottos;
    }

    @Override
    public void printPrizeResult(Prizes prizes, BigDecimal profitPercent) {
        this.prizes = prizes;
        this.profitPercent = profitPercent;
    }
}
