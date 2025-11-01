package lotto.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static lotto.error.ErrorMessage.OUT_OF_NUMBER_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberTest {

    @Nested
    @DisplayName("초기화 테스트")
    class InitTests {
        @DisplayName("범위 이외의 수를 입력했을 때 예외를 발생시킨다.")
        @Test
        void shouldThrowException_WhenOutOfRange() {
            assertThatThrownBy(() -> new Number(46))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(OUT_OF_NUMBER_RANGE.formatMessage(1,45));
        }

        @DisplayName("범위 이내의 수를 입력했을 때 객체를 생성할 수 있다.")
        @Test
        void canMakeNumber() {
            Number number = new Number(43);
            assertThat(number.getNumber()).isEqualTo(43);
        }
    }

    @Nested
    @DisplayName("기능 테스트")
    class FunctionTests {
        Number number1 = new Number(10);
        Number number2 = new Number(10);

        @DisplayName("같은 숫자라면, 같은 객체로 판단한다.")
        @Test
        void shouldJudgeAsEqualObject_whenNumberIsSame() {
            assertThat(number1.equals(number2)).isTrue();
        }

        @DisplayName("Number를 출력 시에, 안에 있는 int값이 출력된다.")
        @Test
        void shouldReturnIntWhenCallToString() {
            assertThat(number1.toString()).isEqualTo("10");
        }
    }
}