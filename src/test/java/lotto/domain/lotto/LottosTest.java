package lotto.domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        lotto2 = new Lotto(List.of(1,2,3,4,5,6));
        lotto3 = new Lotto(List.of(1,2,3,4,5,6));
        lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
    }

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
        assertThat(lottos.getLottos().size()).isEqualTo(3);
        assertThat(lottos.getLottos()).containsExactly(lotto1, lotto2, lotto3);
    }
}