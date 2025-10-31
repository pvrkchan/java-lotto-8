package lotto;

import lotto.domain.Lottos;
import lotto.service.LottoService;
import lotto.ui.UserInterface;
import lotto.view.OutputView;

public class LottoController {
    private UserInterface userInterface;
    private OutputView outputView;
    private LottoService lottoService;

    public LottoController(UserInterface userInterface, OutputView outputView, LottoService lottoService) {
        this.userInterface = userInterface;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Lottos lottos  = new Lottos(lottoService.buyLotto(userInterface.readMoneyAmount()));
        outputView.printBuyingResult(lottos);
    }
}
