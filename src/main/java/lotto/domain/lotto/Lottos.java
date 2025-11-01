package lotto.domain.lotto;

import java.util.List;

import static lotto.error.ErrorMessage.LOTTOS_EMPTY;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        validateNotEmpty(lottos);
        this.lottos = lottos;
    }

    private void validateNotEmpty(List<Lotto> lottos) {
        if (lottos == null || lottos.isEmpty()) {
            System.out.println(LOTTOS_EMPTY.message());
            throw new IllegalArgumentException(LOTTOS_EMPTY.message());
        }
    }

    public int getSize() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
