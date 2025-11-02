package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.number.WinningInformation;
import lotto.domain.prize.Prize;
import lotto.repository.WinningInformationStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoController 테스트")
class LottoControllerTest {

    @Nested
    @DisplayName("입력 받기 테스트")
    class UserInterfaceTest {
        StubUserInterface  userInterface;
        SpyOutputView outputView;
        StubLottoService lottoService;
        LottoController lottoController;

        @BeforeEach
        void setUp() {
            userInterface = new StubUserInterface(1000, "1,2,3,4,5,6", 7);
            outputView = new SpyOutputView();
            lottoService = new StubLottoService(
                    new Lottos(List.of(new Lotto(List.of(1,2,3,4,5,6)))),
                    List.of(Prize.SIX)
            );
            lottoController = new LottoController(userInterface,outputView,lottoService);
        }

        @Test
        @DisplayName("Controller는 UserInterface를 통해 돈의 액수를 입력받는다.")
        void shouldReadMoneyAmount() {
            //when
            lottoController.run();

            //then
            assertThat(userInterface.readMoneyAmountCalled).isTrue();
        }

        @Test
        @DisplayName("Controller는 UserInterface를 통해 당첨 번호를 입력받는다.")
        void shouldReadWinningNumbers() {
            //when
            lottoController.run();

            //then
            assertThat(userInterface.readWinningNumbersCalled).isTrue();
        }

        @Test
        @DisplayName("Controller는 UserInterface를 통해 보너스 번호를 입력받는다.")
        void shouldReadBonusNumber() {
            //when
            lottoController.run();

            //then
            assertThat(userInterface.readBonusNumberCalled).isTrue();
        }
    }

    @Nested
    @DisplayName("LottoService 호출 테스트")
    class LottoServiceCallTest {
        StubUserInterface userInterface;
        SpyOutputView outputView;
        StubLottoService lottoService;
        LottoController lottoController;

        @BeforeEach
        void setUp() {
            userInterface = new StubUserInterface(1000, "1,2,3,4,5,6", 7);
            outputView = new SpyOutputView();
            lottoService = new StubLottoService(
                    new Lottos(List.of(new Lotto(List.of(1,2,3,4,5,6)))),
                    List.of(Prize.SIX)
            );
            lottoController = new LottoController(userInterface, outputView, lottoService);
        }

        @Test
        @DisplayName("Controller는 LottoService.buyLotto를 호출한다.")
        void shouldCallBuyLotto() {
            //when
            lottoController.run();

            //then
            assertThat(lottoService.buyLottoCalled).isTrue();
            assertThat(lottoService.receivedMoney).isEqualTo(1000);
        }

        @Test
        @DisplayName("Controller는 LottoService.registerWinningInformation를 호출한다.")
        void shouldCallRegisterWinningInformation() {
            //when
            lottoController.run();

            //then
            assertThat(lottoService.registerWinningInformationCalled).isTrue();
            assertThat(lottoService.receivedWinningNumbers.getWinningNumbers()).containsExactly(1,2,3,4,5,6);
            assertThat(lottoService.receivedBonusNumber.getBonusNumber()).isEqualTo(7);
        }

        @Test
        @DisplayName("Controller는 LottoService.getPrizes를 호출한다.")
        void shouldCallGetPrizes() {
            //when
            lottoController.run();

            //then
            assertThat(lottoService.getPrizesCalled).isTrue();
            assertThat(lottoService.getPrizes()).containsExactly(Prize.SIX);
        }

    }

    @Nested
    @DisplayName("OutputView 호출 테스트")
    class OutputViewCallTest {
        StubUserInterface userInterface;
        SpyOutputView outputView;

        @BeforeEach
        void setUp() {
            userInterface = new StubUserInterface(1000, "1,2,3,4,5,6", 7);
            outputView = new SpyOutputView();
        }

        @Test
        @DisplayName("Controller는 LottoService로부터 받은 구매 복권 리스트를 OutputView로 전달한다.")
        void shouldPrintBuyingResult() {
            //given
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1,2,3,4,5,6))));
            List<Prize> prizes = List.of(Prize.SIX);
            WinningInformation winningInformation= new WinningInformationStorage();
            StubLottoService lottoService = new StubLottoService(lottos, prizes);
            LottoController lottoController = new LottoController(userInterface, outputView, lottoService);

            //when
            lottoController.run();

            //then
            assertThat(outputView.lottos).isEqualTo(lottos);
        }

        @Test
        @DisplayName("Controller는 LottoService로부터 받은 당첨 금액 리스트와 Computer에서 계산한 수익률을 OutputView로 전달한다.")
        void shouldPrintPrizeResult() {
            //given
            Lottos lottos = new Lottos(List.of(new Lotto(List.of(1,2,3,8,9,10))));
            List<Prize> prizes = List.of(Prize.THREE);
            WinningInformation winningInformation= new WinningInformationStorage();
            StubLottoService lottoService = new StubLottoService(lottos, prizes);
            LottoController lottoController = new LottoController(userInterface, outputView, lottoService);

            //when
            lottoController.run();

            //then
            assertThat(outputView.prizes.getPrizes()).isEqualTo(prizes);
            assertThat(outputView.profitPercent).isEqualTo(new BigDecimal("500.0"));
        }
    }
}