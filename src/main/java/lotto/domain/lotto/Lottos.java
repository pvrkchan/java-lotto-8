package lotto.domain.lotto;

import lotto.domain.number.WinningInformation;
import lotto.domain.prize.Prize;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Prize> CheckAllLottosWinning(WinningInformation winningInformation) {
        List<Prize> prizes = new ArrayList<>();
        for (Lotto lotto : lottos) {
            Optional<Prize> optionalPrize = lotto.checkWinning(winningInformation);
            optionalPrize.ifPresent(prizes::add);
        }
        return prizes;
    }
}
