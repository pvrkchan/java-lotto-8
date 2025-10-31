package lotto.domain;

import java.util.Objects;

import static lotto.error.ErrorMessage.OUT_OF_NUMBER_RANGE;

public class Number {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;

    private int number;

    public Number(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException(OUT_OF_NUMBER_RANGE.formatMessage(MIN_VALUE, MAX_VALUE));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public String toString() {
        return number + "";
    }

    public int getNumber() {
        return number;
    }
}
