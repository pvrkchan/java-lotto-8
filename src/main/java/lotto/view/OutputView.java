package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {
    private static final String BUYING_PROMPT = "%d개를 구매했습니다.";

    public void printBuyingResult(Lottos lottos) {
        System.out.println();
        System.out.printf((BUYING_PROMPT) + "%n",(lottos.getSize()));
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }
}