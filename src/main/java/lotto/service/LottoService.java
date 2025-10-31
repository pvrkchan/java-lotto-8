package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.util.RandomPicker;

import java.util.ArrayList;
import java.util.List;

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
}
