package lotto.service;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoStorage;
import lotto.domain.lotto.Lottos;
import lotto.domain.number.BonusNumber;
import lotto.domain.number.Number;
import lotto.domain.number.WinningInformation;
import lotto.domain.number.WinningNumbers;
import lotto.domain.prize.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceImplTest {
    LottoService lottoService;
    LottoStorage fakeLottoMemoryStorage;
    WinningInformation fakeWinningInformationStorage;


    @BeforeEach
    void setUp() {
        fakeLottoMemoryStorage = new FakeLottoMemoryStorage();
        fakeWinningInformationStorage = new FakeWinningInformationStorage();
        lottoService = new LottoServiceImpl(fakeLottoMemoryStorage, fakeWinningInformationStorage);
    }

    @Nested
    @DisplayName("로또 구매 테스트")
    class BuyLottoTests {
        @DisplayName("3000원으로 로또 3장을 구매할 수 있다.")
        @Test
        void canBuyLottoWithMoney() {
            assertThat(lottoService.buyLotto(3000).getSize()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("당첨 정보 등록 테스트")
    class RegisterWinningInformationTests {
        @DisplayName("당첨 번호 및 보너스 번호를 등록할 수 있다.")
        @Test
        void canRegisterWinningInformation() {
            //given
            WinningNumbers winningNumbers = new WinningNumbers(List.of(
                    new Number(1), new Number(2), new Number(3),
                    new Number(4), new Number(5), new Number(6)
            ));
            BonusNumber bonusNumber = new BonusNumber(new Number(7), winningNumbers);

            //when
            lottoService.registerWinningInformation(winningNumbers, bonusNumber);
            WinningInformation winningInformation = lottoService.getWinningInformationStorage();

            //then
            assertThat(winningInformation.getWinningNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(winningInformation.getBonusNumber()).isEqualTo(7);
        }
    }


    @Nested
    @DisplayName("당첨 정보 확인 테스트")
    class CheckWinningInformationTests {
        @DisplayName("로또들의 당첨 결과를 확인할 수 있다.")
        @Test
        void canCheckWinningInformation() {
            //given
            WinningNumbers winningNumbers = new WinningNumbers(List.of(
                    new Number(1), new Number(2), new Number(3),
                    new Number(4), new Number(5), new Number(6)
            ));
            BonusNumber bonusNumber = new BonusNumber(new Number(7), winningNumbers);
            Lottos lottos = new Lottos(List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 8, 9, 10)),
                    new Lotto(List.of(1, 2, 3, 4, 5, 7)), new Lotto(List.of(1, 2, 3, 4, 8, 9))
            ));

            //when
            fakeLottoMemoryStorage.save(lottos);
            fakeWinningInformationStorage.save(winningNumbers, bonusNumber);
            lottoService = new LottoServiceImpl(fakeLottoMemoryStorage, fakeWinningInformationStorage);

            //then
            assertThat(lottoService.getPrizes()).containsExactly(Prize.SIX, Prize.THREE, Prize.FIVE_BONUS, Prize.FOUR);
        }
    }
}