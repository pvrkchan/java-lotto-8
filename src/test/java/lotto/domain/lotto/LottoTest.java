package lotto.domain.lotto;

import lotto.domain.number.BonusNumber;
import lotto.domain.number.Number;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import lotto.repository.WinningInformationStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Nested
    @DisplayName("초기화 테스트")
    class InitTests {
        @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
        @Test
        void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
        @Test
        void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("출력 테스트")
    class PrintTest {
        @DisplayName("로또를 출력하면 해당 로또 번호가 출력되어야 한다.")
        @Test
        void shouldReturnToStringTest() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when, then
            assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
        }
    }

    @Nested
    @DisplayName("당첨 여부 확인 테스트")
    class checkWinningTests {


        List<Number> numbers = List.of(
                new Number(1), new Number(2), new Number(3),
                new Number(4), new Number(5), new Number(6)
        );
        WinningNumbers winningNumbers = new WinningNumbers(numbers);
        BonusNumber bonusNumber = new BonusNumber(new Number(7), winningNumbers);
        WinningInformationStorage winningInformationStorage = new WinningInformationStorage();
        @BeforeEach
        void setUp() {
            winningInformationStorage.save(winningNumbers,bonusNumber);
        }

        @DisplayName("로또의 당첨 여부를 확인할 수 있다.")
        @Test
        void canCheckWinningOfLotto() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when, then
            assertThat(lotto.checkWinning(winningInformationStorage)).isEqualTo(Optional.of(Prize.SIX));
        }

        @DisplayName("로또의 보너스 당첨 여부를 확인할 수 있다.")
        @Test
        void canCheckBonusWinningOfLotto() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

            //when, then
            assertThat(lotto.checkWinning(winningInformationStorage)).isEqualTo(Optional.of(Prize.FIVE_BONUS));
        }
    }
}
