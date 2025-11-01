package lotto.domain.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {


    @Nested
    @DisplayName("초기화 테스트")
    class InitTests {
        Money money;

        @DisplayName("로또 금액으로 나누어 떨어지지 않는 구매 금액을 입력하는 경우 예외를 발생시킨다.")
        @Test
        void shouldThrowException_whenCannotDivide() {
            assertThatThrownBy(() -> money.from(800))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("구매 금액으로 음수를 입력하는 경우 예외를 발생시킨다.")
        @Test
        void shouldThrowException_whenMoneyIsNegative() {
            assertThatThrownBy(() -> money.from(-1000))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("기능 테스트")
    class FunctionTest {
        Money money;

        @BeforeEach
        void setUp() {
            money = Money.from(1000);
        }

        @DisplayName("Money.pay() 메서드를 호출하면, 로또 금액만큼 차감된 잔액을 가진 새로운 Money가 반환된다.")
        @Test
        void shouldReturnBalanceAfterPay() {
            //when
            Money afterPayMoney = money.pay();

            //then
            assertThat(afterPayMoney.getBalance()).isEqualTo(0);
        }

        @DisplayName("Money.isRunOutOf() 메서드를 호출하면, 돈의 소진 유무를 반환한다.")
        @Test
        void shouldReturnTrueWhenMoneyIsRunOutOf() {
            //when
            Money afterPayMoney = money.pay();

            //then
            assertThat(afterPayMoney.isRunOutOf()).isTrue();
        }
    }


}