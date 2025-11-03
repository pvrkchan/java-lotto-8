package lotto.util;

import lotto.domain.number.Number;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.error.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThat;

class SeperatorTest {

    @DisplayName(",를 기준으로 입력된 숫자를 구분하여 리스트를 생성한다.")
    @Test
    void seperateWithSeparator() {
        //given
        String string = "1,2,3,4,5,6";

        //when
        List<Number> numbers = Seperator.numberSplit(string);

        //then
        assertThat(numbers).hasSize(6);
        assertThat(numbers.stream().map(Number::getNumber).toList()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("숫자가 아닌 것이 입력될 시 예외를 발생시킨다.")
    @Test
    void shouldThrowException_notANumber() {
        String string = "a,b,c,d";

        Assertions.assertThatThrownBy(() -> Seperator.numberSplit(string))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SEPERATE_NOT_NUMBER.message());
    }

    @DisplayName("입력 문자열이 ,로 끝날 시에 예외를 발생시킨다.")
    @Test
    void shouldThrowException_endWithSeparator() {
        String string = "1,2,3,4,5,6,";

        Assertions.assertThatThrownBy(() -> Seperator.numberSplit(string))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(END_WITH_SEPERATOR.message());
    }

    @DisplayName("입력 문자열이 null일 경우, 예외를 발생시킨다.")
    @Test
    void shouldThrowException_null() {
        String string = null;

        Assertions.assertThatThrownBy(() -> Seperator.numberSplit(string))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SEPERATE_NULL.message());
    }

}