package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.money.Money;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import lotto.repository.LottoMemoryStorage;
import lotto.repository.WinningInformationStorage;
import lotto.util.RandomPicker;

import java.util.ArrayList;
import java.util.List;

public class LottoServiceImpl implements LottoService {
    private LottoMemoryStorage lottoMemoryStorage;
    private WinningInformationStorage winningInformationStorage;

    public LottoServiceImpl(LottoMemoryStorage storage, WinningInformationStorage WinningInformationStorage) {
        this.lottoMemoryStorage = storage;
        this.winningInformationStorage = new WinningInformationStorage();
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
        lottoMemoryStorage.save(lottos);
        return lottos;
    }

    @Override
    public void registerWinningInformation(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        winningInformationStorage.save(winningNumbers, bonusNumber);
    }

    @Override
    public List<Prize> getPrizes() {
        Lottos lottos = lottoMemoryStorage.getLottos();
        List<Prize> prizes = lottos.CheckAllLottosWinning(winningInformationStorage);
        return prizes;
    }
}
