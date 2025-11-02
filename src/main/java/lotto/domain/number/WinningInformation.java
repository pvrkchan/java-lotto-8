package lotto.domain.number;

import java.util.List;

public interface WinningInformation {
    void save(WinningNumbers winningNumbers, BonusNumber bonusNumber);

    List<Integer> getWinningNumbers();

    int getBonusNumbers();
}
