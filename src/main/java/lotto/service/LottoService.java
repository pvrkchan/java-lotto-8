package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;

import java.util.List;
import java.util.Optional;

public interface LottoService {

    List<Lotto> buyLotto(final int money);

    List<Prize> checkPrize(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber);

    Optional<Prize> checkEachLotto(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber);

    Prize checkBonusNumber(Lotto lotto, BonusNumber bonusNumber);
}
