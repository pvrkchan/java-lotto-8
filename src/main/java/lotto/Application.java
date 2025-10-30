package lotto;

import lotto.service.LottoService;
import lotto.ui.ConsoleUserInterface;
import lotto.view.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleUserInterface consoleUserInterface = new ConsoleUserInterface();
        ConsoleOutputView consoleOutputView = new ConsoleOutputView();
        LottoService lottoService = new LottoService();

        LottoController lottoController = new LottoController(consoleUserInterface, consoleOutputView, lottoService);
        lottoController.run();
    }
}
