package performance.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int readMenuChoice() {
        System.out.print("분석할 항목의 번호를 입력하세요: ");

        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("입력 스트림이 종료되었습니다.");
        }

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

    public int readInputIterations(String guideMessage) {
        System.out.println("\n" + guideMessage);
        System.out.print("분석 반복 횟수를 입력하세요: ");

        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("입력 스트림이 종료되었습니다.");
        }

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 없습니다. 반복 횟수를 입력해주세요.");
        }

        try {
            int iterations = Integer.parseInt(input);
            if (iterations <= 0) {
                throw new IllegalArgumentException("반복 횟수는 1 이상의 양수여야 합니다.");
            }
            return iterations;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public boolean confirmSave() {
        while (true) {
            System.out.print("\n결과를 파일로 저장하시겠습니까? (y/n): ");
            if (!scanner.hasNextLine()) {
                return false;
            }

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            }
            if (input.equals("n")) {
                return false;
            }

            System.out.println("'y' 또는 'n'만 입력해주세요.");
        }
    }

    public void close() {
        scanner.close();
    }
}
