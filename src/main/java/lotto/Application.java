package lotto;

import lotto.controller.LottoController;
import lotto.repository.LottoMemoryStorage;
import lotto.repository.WinningInformationStorage;
import lotto.service.LottoServiceImpl;
import lotto.ui.ConsoleUserInterface;
import lotto.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();
        ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        LottoServiceImpl lottoServiceImpl = new LottoServiceImpl(
                new LottoMemoryStorage(), new WinningInformationStorage()
        );

        LottoController lottoController = new LottoController(consoleUserInterface, consoleOutputView, lottoServiceImpl);
        lottoController.run();
    }
}
