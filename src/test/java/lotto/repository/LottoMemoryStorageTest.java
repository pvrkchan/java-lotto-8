package lotto.repository;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMemoryStorageTest {
    private LottoMemoryStorage lottoMemoryStorage;

    @BeforeEach
    void setUp() {
        lottoMemoryStorage = new LottoMemoryStorage();
    }

    @DisplayName("저장 및 조회 테스트 : Lottos 객체를 저장하고 조회할 수 있다.")
    @Test
    void saveLottos() {
        //give
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        //when
        lottoMemoryStorage.save(lottos);

        //then
        Lottos savedLottos = lottoMemoryStorage.getLottos();
        assertThat(savedLottos).isEqualTo(lottos);
    }


}