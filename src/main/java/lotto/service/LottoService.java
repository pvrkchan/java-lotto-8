package lotto.service;

import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningInformation;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;

import java.util.List;

public interface LottoService {

    Lottos buyLotto(final int money);

    void registerWinningInformation(final WinningNumbers winningNumbers, final BonusNumber bonusNumber);

    List<Prize> getPrizes();

    WinningInformation getWinningInformationStorage();
}
