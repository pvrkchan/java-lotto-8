package lotto.util;

import lotto.domain.Number;

import java.util.Arrays;
import java.util.List;

import static lotto.error.ErrorMessage.END_WITH_SEPERATOR;
import static lotto.error.ErrorMessage.SEPERATE_NOT_NUMBER;

public class Seperator {
    private static final String SEPARATOR = ",";

    public static List<Number> numberSplit(String inputString){
        validateEndWithSeperator(inputString);
        List<Number> numbers;
        try {
            numbers = Arrays.stream(inputString.split(SEPARATOR))
                    .map(Integer::parseInt)
                    .map(lotto.domain.Number::new)
                    .toList();
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException(SEPERATE_NOT_NUMBER.message());
        }
        return numbers;
    }

    private static void validateEndWithSeperator(String inputString) {
        if (inputString.endsWith(SEPARATOR)) {
            throw new IllegalArgumentException(END_WITH_SEPERATOR.message());
        }
    }
}
