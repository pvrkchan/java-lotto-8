package lotto.ui;

import lotto.util.ConsoleReader;

import static lotto.error.ErrorMessage.BONUS_NOT_NUMBER;
import static lotto.error.ErrorMessage.MONEY_AMOUNT_NOT_NUMBER;

public class ConsoleUserInterface implements UserInterface {
    private static final String MONEY_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_PROMPT = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "보너스 번호를 입력해 주세요.";

    @Override
    public int readMoneyAmount() {
        while (true) {
            System.out.println(MONEY_AMOUNT_PROMPT);
            try {
                return Integer.parseInt(ConsoleReader.readLine());
            } catch (NumberFormatException e) {
                System.out.println(MONEY_AMOUNT_NOT_NUMBER.message());
                throw new IllegalArgumentException(MONEY_AMOUNT_NOT_NUMBER.message());
            }
        }
    }

    @Override
    public String readWinningNumbers() {
        System.out.println();
        System.out.println(WINNING_NUMBERS_PROMPT);
        return ConsoleReader.readLine();
    }

    @Override
    public int readBonusNumber() {
        System.out.println();
        System.out.println(BONUS_NUMBER_PROMPT);
        try {
            return Integer.parseInt(ConsoleReader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(BONUS_NOT_NUMBER.message());
            throw new IllegalArgumentException(BONUS_NOT_NUMBER.message());
        }
    }
}
