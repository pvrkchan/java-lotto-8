package lotto;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Number;
import lotto.domain.WinningNumbers;
import lotto.service.LottoService;
import lotto.ui.UserInterface;
import lotto.util.Seperator;
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
        WinningNumbers winningNumbers = new WinningNumbers(Seperator.numberSplit(userInterface.readWinningNumbers()));
        BonusNumber bonusNumber = new BonusNumber(new Number(userInterface.readBonusNumber()),winningNumbers);
    }
}
