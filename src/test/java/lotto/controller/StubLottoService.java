package lotto.controller;

import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningInformation;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import lotto.service.LottoService;

import java.util.List;

public class StubLottoService implements LottoService {

    boolean buyLottoCalled = false;
    int receivedMoney = 0;

    boolean registerWinningInformationCalled = false;
    WinningNumbers receivedWinningNumbers;
    BonusNumber receivedBonusNumber;

    boolean getPrizesCalled = false;


    private final Lottos lottos;
    private final List<Prize> prizes;

    public StubLottoService(Lottos lottos, List<Prize> prizes) {
        this.lottos = lottos;
        this.prizes = prizes;
    }

    @Override
    public Lottos buyLotto(int money) {
        buyLottoCalled = true;
        receivedMoney = money;
        return lottos;
    }

    @Override
    public void registerWinningInformation(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        registerWinningInformationCalled = true;
        receivedWinningNumbers = winningNumbers;
        receivedBonusNumber = bonusNumber;
    }

    @Override
    public List<Prize> getPrizes() {
        getPrizesCalled = true;
        return prizes;
    }

    @Override
    public WinningInformation getWinningInformationStorage() {
        return null;
    }
}