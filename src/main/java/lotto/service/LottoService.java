package lotto.service;

import lotto.domain.*;
import lotto.util.RandomPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LottoService {
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

    public List<Prize> calculatePrize(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        List<Prize> prizes = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            Optional<Prize> optionalPrize = calculateEachLotto(lotto, winningNumbers, bonusNumber);
            optionalPrize.ifPresent(prizes::add);
        }
        return prizes;
    }

    public Optional<Prize> calculateEachLotto(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Long count = lotto.getNumbers().stream()
                .filter(winningNumbers.getWinningNumbers()::contains)
                .count();
        if (count == 3)
            return Optional.of(Prize.THREE);
        if (count == 4)
            return Optional.of(Prize.FOUR);
        if (count == 5)
            return Optional.ofNullable(findBonusNumber(lotto, bonusNumber));
        if (count == 6)
            return Optional.of(Prize.SIX);
        return Optional.empty();
    }

    public Prize findBonusNumber(Lotto lotto, BonusNumber bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber.getBonusNumber())) {
            return Prize.FIVE_BONUS;
        }
        return Prize.FIVE;
    }
}
