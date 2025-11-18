package performance.controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import performance.AnalyzeResult;
import performance.PerformanceAnalyze;
import performance.analysis.StringBuilderAppendAnalyze;
import performance.analysis.StringConcatAnalyze;
import performance.view.InputView;
import performance.view.OutputView;

public class AnalyzeController {
    private static final long DEFAULT_ITERATIONS = 1_000_000L;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Integer, Runnable> menuActions;

    public AnalyzeController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.menuActions = new HashMap<>();
        menuActions.put(1, this::runStringAnalyzes);
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

                Runnable action = menuActions.get(userChoice);
                action.run();
            } catch (InputMismatchException e) {
            }
        }
        inputView.close();
    }

    private void runStringAnalyzes() {
        outputView.printAnalyzeStart("1. String: '+' vs. StringBuilder");

        PerformanceAnalyze slowAnalyze = new StringConcatAnalyze();
        AnalyzeResult slowResult = slowAnalyze.runAnalyze(DEFAULT_ITERATIONS);
        outputView.printAnalyzeResult(slowResult);

        PerformanceAnalyze fastAnalyze = new StringBuilderAppendAnalyze();
        AnalyzeResult fastResult = fastAnalyze.runAnalyze(DEFAULT_ITERATIONS);
        outputView.printAnalyzeResult(fastResult);
    }
}
