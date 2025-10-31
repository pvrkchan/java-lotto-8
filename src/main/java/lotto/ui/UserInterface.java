package lotto.ui;

import lotto.util.ConsoleReader;

import static lotto.error.ErrorMessage.MONEY_AMOUNT_NOT_NUMBER;

public class UserInterface {
    private static final String MONEY_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";

    public int readMoneyAmount() {
        System.out.println(MONEY_AMOUNT_PROMPT);
        try {
            return Integer.parseInt(ConsoleReader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MONEY_AMOUNT_NOT_NUMBER.message());
        }
    }
}
