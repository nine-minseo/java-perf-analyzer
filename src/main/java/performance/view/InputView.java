package performance.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int readMenuChoice() {
        return scanner.nextInt();
    }

    public void close() {
        scanner.close();
    }
}
