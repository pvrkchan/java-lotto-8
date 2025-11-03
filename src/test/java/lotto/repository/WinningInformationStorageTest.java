package lotto.repository;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.Number;
import lotto.domain.number.WinningNumbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningInformationStorageTest {
    private WinningInformationStorage winningInformationStorage;

    @BeforeEach
    void setUp() {
        winningInformationStorage = new WinningInformationStorage();
    }

    @DisplayName("저장 및 조회 테스트 : WinningNumbers 및 BonusNumber 객체를 저장하고 조회할 수 있다.")
    @Test
    void saveWinningInformation() {
        //give
        WinningNumbers winningNumbers = new WinningNumbers(List.of(
                new Number(1), new Number(2), new Number(3),
                new Number(4), new Number(5), new Number(6)
        ));
        BonusNumber bonusNumber = new BonusNumber(new Number(7), winningNumbers);

        //when
        winningInformationStorage.save(winningNumbers, bonusNumber);

        //then
        List<Integer> savedWinningNumbers = winningInformationStorage.getWinningNumbers();
        Integer savedBonusNumber = winningInformationStorage.getBonusNumber();
        assertThat(savedWinningNumbers).isEqualTo(winningNumbers.getWinningNumbers());
        assertThat(savedBonusNumber).isEqualTo(bonusNumber.getBonusNumber());
    }

}