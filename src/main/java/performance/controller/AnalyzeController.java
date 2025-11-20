package performance.controller;

import java.util.HashMap;
import java.util.Map;

import performance.model.AnalyzeResult;
import performance.model.PerformanceAnalyze;
import performance.analysis.string.StringBuilderAppendAnalyze;
import performance.analysis.string.StringConcatAnalyze;
import performance.analysis.list.ArrayListMiddleInsertAnalyze;
import performance.analysis.list.ArrayListSequentialInsertAnalyze;
import performance.analysis.list.LinkedListMiddleInsertAnalyze;
import performance.analysis.list.LinkedListSequentialInsertAnalyze;
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
                if (action == null) {
                    outputView.printError("존재하지 않는 메뉴 번호입니다. 다시 선택해주세요.");
                    continue;
                }
                action.run();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            } catch (IllegalStateException e) {
                outputView.printError("입력이 강제로 종료되었습니다. 프로그램을 종료합니다.");
                break;
            } catch (Exception e) {
                outputView.printError("알 수 없는 오류가 발생했습니다: " + e.getMessage());
            }
        }
        inputView.close();
    }

    private void runStringAnalyzes() {
        outputView.printAnalyzeStart("1. String (+) vs. StringBuilder");
        AnalyzeResult result1 = executeAndPrint(new StringConcatAnalyze(), DEFAULT_ITERATIONS);
        AnalyzeResult result2 = executeAndPrint(new StringBuilderAppendAnalyze(), DEFAULT_ITERATIONS);
        printComparison(result1, result2);
    }

    private void runListMiddleInsertAnalyzes() {
        outputView.printAnalyzeStart("2. ArrayList vs. LinkedList (중간 삽입)");
        AnalyzeResult result1 = executeAndPrint(new ArrayListMiddleInsertAnalyze(), LIST_ITERATIONS);
        AnalyzeResult result2 = executeAndPrint(new LinkedListMiddleInsertAnalyze(), LIST_ITERATIONS);
        printComparison(result1, result2);
    }

    private void runListSequentialInsertAnalyzes() {
        outputView.printAnalyzeStart("3. ArrayList vs. LinkedList (순차 삽입)");
        AnalyzeResult result1 = executeAndPrint(new LinkedListSequentialInsertAnalyze(), LIST_ITERATIONS);
        AnalyzeResult result2 = executeAndPrint(new ArrayListSequentialInsertAnalyze(), LIST_ITERATIONS);
        printComparison(result1, result2);
    }

    private AnalyzeResult executeAndPrint(PerformanceAnalyze analyze, long iterations) {
        AnalyzeResult result = analyze.runAnalyze(iterations);
        String formattedResult = formatter.formatSingleResult(result);
        outputView.printAnalyzeResult(formattedResult);
        return result;
    }

    private void printComparison(AnalyzeResult result1, AnalyzeResult result2) {
        String comparison = formatter.formatComparison(result1, result2);
        outputView.printAnalyzeResult(comparison);
    }
}
