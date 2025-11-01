package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.Number;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import lotto.service.LottoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StubLottoService implements LottoService {

    boolean buyLottoCalled = false;
    int receivedMoney = 0;

    boolean checkPrizeCalled = false;
    List<Number> numberList = List.of(
            new Number(40), new Number(41), new Number(42),
            new Number(43), new Number(44), new Number(45)
    );
    Lottos receivedLottos = new Lottos(new ArrayList<>());
    WinningNumbers receivedWinningNumbers = new WinningNumbers(numberList);

    BonusNumber receivedBonusNumber = new BonusNumber(new Number(39), receivedWinningNumbers);

    private final List<Lotto> lottos;
    private final List<Prize> prizes;
    private final Optional<Prize> optionalPrize;
    private final Prize fivePrize;

    public StubLottoService(List<Lotto> lottos, List<Prize> prizes, Optional<Prize> optionalPrize, Prize fivePrize) {
        this.lottos = lottos;
        this.prizes = prizes;
        this.optionalPrize = optionalPrize;
        this.fivePrize = fivePrize;
    }

    @Override
    public List<Lotto> buyLotto(int money) {
        buyLottoCalled = true;
        receivedMoney = money;
        return lottos;
    }

    @Override
    public List<Prize> checkPrize(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        checkPrizeCalled = true;
        receivedLottos = lottos;
        receivedWinningNumbers = winningNumbers;
        receivedBonusNumber = bonusNumber;
        return prizes;
    }

    @Override
    public Optional<Prize> checkEachLotto(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        return optionalPrize;
    }

    @Override
    public Prize checkBonusNumber(Lotto lotto, BonusNumber bonusNumber) {
        return fivePrize;
    }
}
