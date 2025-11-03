package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.prize.Prize;
import lotto.domain.prize.Prizes;

import java.math.BigDecimal;

public class ConsoleOutputView implements OutputView {
    private static final String BUYING_PROMPT = "%d개를 구매했습니다.";
    private static final String PRIZE_RESULT_PROMPT = "당첨 통계\n---";
    private static final String THREE_PRIZE = "3개 일치 (5,000원) - %d개";
    private static final String FOUR_PRIZE = "4개 일치 (50,000원) - %d개";
    private static final String FIVE_PRIZE = "5개 일치 (1,500,000원) - %d개";
    private static final String BONUS_PRIZE = "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개";
    private static final String SIX_PRIZE = "6개 일치 (2,000,000,000원) - %d개";
    private static final String PROFIT_PROMPT = "총 수익률은 %.1f%%입니다.";

    @Override
    public void printBuyingResult(Lottos lottos) {
        System.out.println();
        System.out.printf((BUYING_PROMPT) + "%n", (lottos.getSize()));
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    @Override
    public void printPrizeResult(Prizes prizes, BigDecimal profitPercent) {
        System.out.println();
        System.out.println(PRIZE_RESULT_PROMPT);
        System.out.printf(THREE_PRIZE + "%n", prizes.getFrequency(Prize.THREE));
        System.out.printf(FOUR_PRIZE + "%n", prizes.getFrequency(Prize.FOUR));
        System.out.printf(FIVE_PRIZE + "%n", prizes.getFrequency(Prize.FIVE));
        System.out.printf(BONUS_PRIZE + "%n", prizes.getFrequency(Prize.FIVE_BONUS));
        System.out.printf(SIX_PRIZE + "%n", prizes.getFrequency(Prize.SIX));
        System.out.printf(PROFIT_PROMPT + "%n", profitPercent);
    }
}