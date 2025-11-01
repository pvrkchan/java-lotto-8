package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.ui.UserInterface;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService();

        LottoController lottoController = new LottoController(userInterface, outputView, lottoService);
        lottoController.run();
    }
}
