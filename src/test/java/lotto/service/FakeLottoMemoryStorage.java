package lotto.service;

import lotto.domain.lotto.LottoStorage;
import lotto.domain.lotto.Lottos;

public class FakeLottoMemoryStorage implements LottoStorage {
    private Lottos lottos;

    @Override
    public void save(Lottos lottos) {
        this.lottos = lottos;
    }

    @Override
    public Lottos getLottos() {
        return lottos;
    }
}
