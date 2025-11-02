package lotto.domain.lotto;

public interface LottoStorage {

    void save(Lottos lottos);

    Lottos getLottos();
}
