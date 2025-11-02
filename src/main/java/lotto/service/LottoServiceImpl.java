package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoStorage;
import lotto.domain.lotto.Lottos;
import lotto.domain.money.Money;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningInformation;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import lotto.repository.WinningInformationStorage;
import lotto.util.RandomPicker;

import java.util.ArrayList;
import java.util.List;

public class LottoServiceImpl implements LottoService {
    private LottoStorage lottoStorage;
    private WinningInformation winningInformationStorage;

    public LottoServiceImpl(LottoStorage lottoStorage, WinningInformation WinningInformation) {
        this.lottoStorage = lottoStorage;
        this.winningInformationStorage = WinningInformation;
    }

    @Override
    public Lottos buyLotto(final int money) {
        Money balance = Money.from(money);
        List<Lotto> listOfLotto = new ArrayList<>();
        while (!balance.isRunOutOf()) {
            List<Integer> numbers = RandomPicker.generateLottoNumbers();
            listOfLotto.add(new Lotto(numbers));
            balance = balance.pay();
        }
        Lottos lottos = new Lottos(listOfLotto);
        lottoStorage.save(lottos);
        return lottos;
    }

    @Override
    public void registerWinningInformation(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        winningInformationStorage.save(winningNumbers, bonusNumber);
    }

    @Override
    public List<Prize> getPrizes() {
        Lottos lottos = lottoStorage.getLottos();
        List<Prize> prizes = lottos.CheckAllLottosWinning(winningInformationStorage);
        return prizes;
    }

    @Override
    public WinningInformation getWinningInformationStorage() {
        return winningInformationStorage;
    }
}
