package lotto.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static lotto.error.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    Number number1 = new Number(1);
    Number number2 = new Number(2);
    Number number3 = new Number(3);
    Number number4 = new Number(4);
    Number number5 = new Number(5);
    List<Number> numbers;

    @DisplayName("6개의 숫자로 구성되지 않았다면, 예외를 발생시킨다.")
    @Test
    void shouldThrowException_outOfCount() {
        numbers = List.of(number1, number2, number3, number4, number5);
        assertThatThrownBy(() -> new WinningNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_COUNT.formatMessage(6));
    }

    @DisplayName("공백이나 Null 또한 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowException_nullOrEmpty(List<Number> numbers) {
        assertThatThrownBy(() -> new WinningNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EMPTY_WINNING_NUMBERS.message());
    }

    @DisplayName("중복된 숫자가 포함되어 있다면, 예외를 발생시킨다.")
    @Test
    void shouldThrowException_duplicateNumber() {
        Number number6 = new Number(5);
        numbers = List.of(number1, number2, number3, number4, number5, number6);
        assertThatThrownBy(() -> new WinningNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBER_DUPLICATE.message());
    }

    @DisplayName("6개의 숫자를 입력한다면, 객체를 생성할 수 있다.")
    @Test
    void canMakeWinningNumbers() {
        Number number6 = new Number(6);
        numbers = List.of(number1, number2, number3, number4, number5, number6);
        WinningNumbers winningNumbers = new WinningNumbers(numbers);

        assertThat(winningNumbers.getWinningNumbers()).containsExactly(1,2,3,4,5,6);
    }
}