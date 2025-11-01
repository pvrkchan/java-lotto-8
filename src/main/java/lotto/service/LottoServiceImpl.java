package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.money.Money;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import lotto.util.RandomPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LottoServiceImpl implements LottoService {
    @Override
    public List<Lotto> buyLotto(final int money) {
        Money balance = Money.from(money);
        List<Lotto> lottos = new ArrayList<>();
        while (!balance.isRunOutOf()) {
            List<Integer> numbers = RandomPicker.generateLottoNumbers();
            lottos.add(new Lotto(numbers));
            balance = balance.pay();
        }
        return lottos;
    }

    @Override
    public List<Prize> checkPrize(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        List<Prize> prizes = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            Optional<Prize> optionalPrize = checkEachLotto(lotto, winningNumbers, bonusNumber);
            optionalPrize.ifPresent(prizes::add);
        }
        return prizes;
    }

    @Override
    public Optional<Prize> checkEachLotto(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        long matchCount = lotto.getNumbers().stream()
                .filter(winningNumbers.getWinningNumbers()::contains)
                .count();
        if (matchCount == 3)
            return Optional.of(Prize.THREE);
        if (matchCount == 4)
            return Optional.of(Prize.FOUR);
        if (matchCount == 5)
            return Optional.ofNullable(checkBonusNumber(lotto, bonusNumber));
        if (matchCount == 6)
            return Optional.of(Prize.SIX);
        return Optional.empty();
    }

    @Override
    public Prize checkBonusNumber(Lotto lotto, BonusNumber bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber.getBonusNumber())) {
            return Prize.FIVE_BONUS;
        }
        return Prize.FIVE;
    }
}
