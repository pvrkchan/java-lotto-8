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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static lotto.error.ErrorMessage.LOTTOS_EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {
    Lotto lotto1;
    Lotto lotto2;
    Lotto lotto3;
    Lottos lottos;

    @BeforeEach
    void setUp() {
        lotto1 = new Lotto(List.of(1,2,3,4,5,6));
        lotto2 = new Lotto(List.of(1,2,3,4,5,7));
        lotto3 = new Lotto(List.of(1,2,3,8,9,10));
        lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
    }

    @Nested
    @DisplayName("초기화 테스트")
    class InitTests {
        @DisplayName("로또 목록이 비어있으면 예외를 발생시킨다.")
        @ParameterizedTest
        @NullAndEmptySource
        void shouldThrowException_WhenLottoListIsEmpty(List<Lotto> lottos) {
            assertThatThrownBy(() -> new Lottos(lottos))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(LOTTOS_EMPTY.message());
        }

        @DisplayName("로또 목록을 받아 Lottos 객체를 생성할 수 있다.")
        @Test
        void shouldSuccessNotEmpty() {
            assertThat(lottos.getSize()).isEqualTo(3);
            assertThat(lottos.getLottos()).containsExactly(lotto1, lotto2, lotto3);
        }
    }

    @Nested
    @DisplayName("기능 테스트")
    class FuntionTests {
        @DisplayName("로또 목록에 있는 로또들의 당첨 여부를 확인할 수 있다.")
        @Test
        void canCheckAllLottos() {
            //given
            List<lotto.domain.number.Number> numbers = List.of(
                    new lotto.domain.number.Number(1), new lotto.domain.number.Number(2), new lotto.domain.number.Number(3),
                    new lotto.domain.number.Number(4), new lotto.domain.number.Number(5), new lotto.domain.number.Number(6)
            );
            WinningNumbers winningNumbers = new WinningNumbers(numbers);
            BonusNumber bonusNumber = new BonusNumber(new Number(7), winningNumbers);
            WinningInformationStorage winningInformationStorage = new WinningInformationStorage();
            winningInformationStorage.save(winningNumbers, bonusNumber);

            assertThat(lottos.CheckAllLottosWinning(winningInformationStorage)).hasSize(3);
            assertThat(lottos.CheckAllLottosWinning(winningInformationStorage)).containsExactly(
                    Prize.SIX, Prize.FIVE_BONUS, Prize.THREE
            );
        }
    }
}