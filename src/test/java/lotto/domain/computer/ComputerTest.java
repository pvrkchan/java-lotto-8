package lotto.domain.computer;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ComputerTest {

    @DisplayName("Lottos와 총 상금 액수를 받아 수익률을 계산하여 반환한다.")
    @Test
    void shouldReturnProfitPercent() {
        // given
        Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))));
        BigDecimal totalPrizeMoney = new BigDecimal("5000");

        // when
        BigDecimal profitPercent = Computer.computeProfit(lottos, totalPrizeMoney);

        // then
        assertThat(profitPercent).isEqualTo(new BigDecimal("500.0"));
    }
}