package performance.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int readMenuChoice() {
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 없습니다. 메뉴의 번호를 입력해주세요.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public void close() {
        scanner.close();
    }
}
