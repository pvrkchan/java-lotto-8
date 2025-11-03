package lotto.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RandomPickerTest {
    List<Integer> numbers = RandomPicker.generateLottoNumbers();

    @DisplayName("RandomPicker는 중복되지 않는 6개의 숫자를 반환한다.")
    @Test
    void shouldMakeUniqueSixNumbers() {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        assertThat(numbers.size()).isEqualTo(6);
        assertThat(uniqueNumbers.size()).isEqualTo(numbers.size());
    }

    @DisplayName("RandomPicker가 반환하는 숫자의 범위는 1과 45 사이에 있다.")
    @Test
    void shouldMakeSixNumbers() {
        assertThat(numbers).allMatch(number -> number >= 1 && number <= 45);
    }


}