package lotto;

import lotto.service.LottoService;
import lotto.ui.ConsoleUserInterface;
import lotto.view.ConsoleOutputView;

public class LottoController {
    private ConsoleUserInterface consoleUserInterface;
    private ConsoleOutputView consoleOutputView;
    private LottoService lottoService;

    public LottoController(ConsoleUserInterface consoleUserInterface, ConsoleOutputView consoleOutputView, LottoService lottoService) {
        this.consoleUserInterface = consoleUserInterface;
        this.consoleOutputView = consoleOutputView;
        this.lottoService = lottoService;
    }

    public void run() {
        lottoService.buyLotto(consoleUserInterface.readMoneyAmount());
    }
}
