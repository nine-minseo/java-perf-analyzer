package performance.controller;

import java.util.InputMismatchException;
import performance.view.InputView;
import performance.view.OutputView;

public class AnalyzeController {
    private final InputView inputView;
    private final OutputView outputView;

    public AnalyzeController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            outputView.printMainMenu();
            try {
                int userChoice = inputView.readMenuChoice();

                if (userChoice == 0) {
                    outputView.printProgramExit();
                    break;
                }
            } catch (InputMismatchException e) {
            }
        }
    }
}
