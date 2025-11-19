package performance.controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import performance.AnalyzeResult;
import performance.PerformanceAnalyze;
import performance.analysis.StringBuilderAppendAnalyze;
import performance.analysis.StringConcatAnalyze;
import performance.analysis.ArrayListMiddleInsertAnalyze;
import performance.analysis.ArrayListSequentialInsertAnalyze;
import performance.analysis.LinkedListMiddleInsertAnalyze;
import performance.analysis.LinkedListSequentialInsertAnalyze;
import performance.view.InputView;
import performance.view.OutputView;
import performance.view.ResultFormatter;

public class AnalyzeController {
    private static final long DEFAULT_ITERATIONS = 50_000L;
    private static final long LIST_ITERATIONS = 100_000L;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Integer, Runnable> menuActions;
    private final ResultFormatter formatter;

    public AnalyzeController(InputView inputView, OutputView outputView, ResultFormatter formatter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.formatter = formatter;
        this.menuActions = new HashMap<>();

        initializeMenuActions();
    }

    public void initializeMenuActions() {
        menuActions.put(1, this::runStringAnalyzes);
        menuActions.put(2, this::runListMiddleInsertAnalyzes);
        menuActions.put(3, this::runListSequentialInsertAnalyzes);
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
        executeAndPrint(new StringConcatAnalyze(), DEFAULT_ITERATIONS);
        executeAndPrint(new StringBuilderAppendAnalyze(), DEFAULT_ITERATIONS);
    }

    private void runListMiddleInsertAnalyzes() {
        outputView.printAnalyzeStart("2. ArrayList vs. LinkedList (중간 삽입)");
        executeAndPrint(new ArrayListMiddleInsertAnalyze(), LIST_ITERATIONS);
        executeAndPrint(new LinkedListMiddleInsertAnalyze(), LIST_ITERATIONS);
    }

    private void runListSequentialInsertAnalyzes() {
        outputView.printAnalyzeStart("3. ArrayList vs. LinkedList (순차 삽입)");
        executeAndPrint(new LinkedListSequentialInsertAnalyze(), LIST_ITERATIONS);
        executeAndPrint(new ArrayListSequentialInsertAnalyze(), LIST_ITERATIONS);
    }

    private void executeAndPrint(PerformanceAnalyze analyze, long iterations) {
        AnalyzeResult result = analyze.runAnalyze(iterations);
        String formattedResult = formatter.formatSingleResult(result);
        outputView.printAnalyzeResult(formattedResult);
    }
}
