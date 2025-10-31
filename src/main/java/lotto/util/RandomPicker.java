package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomPicker {
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 45;
    private static final int AMOUNT = 6;

    public static List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, AMOUNT)
                .stream()
                .sorted()
                .toList();
    }
}
