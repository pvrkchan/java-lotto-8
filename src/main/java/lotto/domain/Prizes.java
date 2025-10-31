package lotto.domain;

import java.math.BigInteger;
import java.util.List;

public class Prizes {
    private final List<Prize> prizes;

    public Prizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }
}
