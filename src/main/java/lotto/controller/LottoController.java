package lotto.controller;

import lotto.domain.computer.Computer;
import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.Number;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prizes;
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
        Lottos lottos = makeLottos();
        outputView.printBuyingResult(lottos);

        WinningNumbers winningNumbers = makeWinningNumbers();
        BonusNumber bonusNumber = makeBonusNumber(winningNumbers);
        lottoService.registerWinningInformation(winningNumbers, bonusNumber);

        Prizes prizes = new Prizes(lottoService.getPrizes());
        outputView.printPrizeResult(prizes, Computer.computeProfit(lottos, prizes.getTotalPrizeMoney()));
    }

    private Lottos makeLottos() {
        while (true) {
            try {
                return lottoService.buyLotto(userInterface.readMoneyAmount());
            } catch (IllegalArgumentException e) {
            }
        }
    }

    private WinningNumbers makeWinningNumbers() {
        while (true) {
            try {
                return new WinningNumbers(Seperator.numberSplit(userInterface.readWinningNumbers()));
            } catch (IllegalArgumentException e) {
            }
        }
    }

    private BonusNumber makeBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                return new BonusNumber(new Number(userInterface.readBonusNumber()), winningNumbers);
            } catch (IllegalArgumentException e) {
            }
        }
    }
}