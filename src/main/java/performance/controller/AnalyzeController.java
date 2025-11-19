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
import performance.view.ResultFormatter;

public class AnalyzeController {
    private static final long DEFAULT_ITERATIONS = 1_000_000L;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Integer, Runnable> menuActions;
    private final ResultFormatter formatter;

    public AnalyzeController(InputView inputView, OutputView outputView, ResultFormatter formatter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.formatter = formatter;
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
        outputView.printAnalyzeStart("1. String (+) vs. StringBuilder");

        PerformanceAnalyze slowAnalyze = new StringConcatAnalyze();
        AnalyzeResult slowResult = slowAnalyze.runAnalyze(DEFAULT_ITERATIONS);
        String formattedSlowResult = formatter.formatSingleResult(slowResult);
        outputView.printAnalyzeResult(formattedSlowResult);

        PerformanceAnalyze fastAnalyze = new StringBuilderAppendAnalyze();
        AnalyzeResult fastResult = fastAnalyze.runAnalyze(DEFAULT_ITERATIONS);
        String formattedFastResult = formatter.formatSingleResult(fastResult);
        outputView.printAnalyzeResult(formattedFastResult);
    }
}
