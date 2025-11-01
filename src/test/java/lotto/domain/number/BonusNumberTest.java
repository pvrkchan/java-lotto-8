package lotto.domain.number;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.error.ErrorMessage.BONUS_NUMBER_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThat;

class BonusNumberTest {
    WinningNumbers winningNumbers = new WinningNumbers(List.of(
            new Number(1), new Number(2), new Number(3),
            new Number(4), new Number(5), new Number(6)
    ));

    @DisplayName("당첨번호와 중복된 보너스 번호 입력 시에 예외를 발생시킨다.")
    @Test
    void shouldThrowException_whenDuplicateWithWinningNumbers() {
        Assertions.assertThatThrownBy(() -> new BonusNumber(new Number(1), winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_DUPLICATE.message());
    }

    @DisplayName("정상적인 보너스 번호 입력 시 BonusNumber 객체를 생성할 수 있다.")
    @Test
    void canMakeBonusNumber() {
        BonusNumber bonusNumber = new BonusNumber(new Number(7), winningNumbers);

        assertThat(bonusNumber.getBonusNumber()).isEqualTo(7);
    }

}