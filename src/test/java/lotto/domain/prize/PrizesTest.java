package lotto.domain.prize;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.util.List;

import static lotto.error.ErrorMessage.PRIZES_NULL;
import static org.assertj.core.api.Assertions.assertThat;

class PrizesTest {
    List<Prize> listOfPrize = List.of(Prize.THREE, Prize.THREE, Prize.FOUR, Prize.FIVE);

    @Nested
    @DisplayName("초기화 테스트")
    class InitTests {
        @DisplayName("비어있는 당첨 리스트로도 객체를 생성할 수 있다.")
        @ParameterizedTest
        @EmptySource
        void canMakePrizesWithEmptyPrizes(List<Prize> listOfPrize) {
            Prizes prizes = new Prizes(listOfPrize);

            assertThat(prizes.getPrizes()).isEmpty();
        }

        @DisplayName("당첨 리스트들로 객체를 생성할 수 있다.")
        @Test
        void canMakePrizesWithPrizes() {
            Prizes prizes = new Prizes(listOfPrize);

            assertThat(prizes.getPrizes()).containsExactly(Prize.THREE, Prize.THREE, Prize.FOUR, Prize.FIVE);
        }

        @DisplayName("상 목록이 Null 값일 때, 예외를 발생시킨다.")
        @ParameterizedTest
        @NullSource
        void shouldThrowException_whenWithNullPrizes(List<Prize> prizes) {
            Assertions.assertThatThrownBy(() -> new Prizes(prizes))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(PRIZES_NULL.message());
        }
    }

    @Nested
    @DisplayName("기능 테스트")
    class FunctionTests {
        Prizes prizes = new Prizes(listOfPrize);

        @DisplayName("각 상이 몇개씩 나왔는 지 조회할 수 있습니다.")
        @Test
        void canLookUpCountOfEachPrizes() {
            assertThat(prizes.getFrequency(Prize.THREE)).isEqualTo(2);
        }

        @DisplayName("발행한 로또의 총 상금의 합을 구할 수 있습니다.")
        @Test
        void canCalculateTotalPrizeMoney() {
            BigDecimal totalPrizeMoney = new BigDecimal(1_560_000);
            assertThat(prizes.getTotalPrizeMoney()).isEqualTo(totalPrizeMoney);
        }
    }
}