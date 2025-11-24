package performance.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import performance.analysis.array.ForLoopCopyAnalyze;
import performance.analysis.array.SystemArrayCopyAnalyze;
import performance.analysis.collection.ArrayListContainsAnalyze;
import performance.analysis.collection.HashSetContainsAnalyze;
import performance.analysis.list.ArrayListRemoveByIndexAnalyze;
import performance.analysis.list.ArrayListRemoveByIteratorAnalyze;
import performance.analysis.map.HashMapGetAnalyze;
import performance.analysis.map.TreeMapGetAnalyze;
import performance.analysis.regex.StringReplaceAllAnalyze;
import performance.analysis.regex.StringReplaceAnalyze;
import performance.analysis.stream.EnhancedForLoopAnalyze;
import performance.analysis.stream.ParallelStreamAnalyze;
import performance.analysis.stream.StreamForEachAnalyze;
import performance.model.AnalyzeResult;
import performance.model.PerformanceAnalyze;
import performance.analysis.string.StringBuilderAppendAnalyze;
import performance.analysis.string.StringConcatAnalyze;
import performance.analysis.list.ArrayListMiddleInsertAnalyze;
import performance.analysis.list.ArrayListSequentialInsertAnalyze;
import performance.analysis.list.LinkedListMiddleInsertAnalyze;
import performance.analysis.list.LinkedListSequentialInsertAnalyze;
import performance.util.FileSaver;
import performance.view.InputView;
import performance.view.OutputView;
import performance.view.ResultFormatter;

public class AnalyzeController {
    private static final String GUIDE_VERY_SLOW = "[가이드] 매우 느린 작업(O(N^2))입니다. \n→ 추천: 10,000 ~ 50,000";
    private static final String GUIDE_SLOW = "[가이드] 느린 작업(O(N))입니다. \n→ 추천: 50,000 ~ 100,000";
    private static final String GUIDE_FAST = "[가이드] 매우 빠른 작업입니다. \n→ 추천: 100,000 ~ 1,000,000 이상";
    private static final String GUIDE_STREAM = "[가이드] 오버헤드를 확인하는 작업입니다. \n → 1,000(오버헤드 큼) vs 1,000,000(병렬 효과)";

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Integer, Runnable> menuActions;
    private final ResultFormatter formatter;
    private final FileSaver fileSaver;
    private final StringBuilder sessionHistory;

    public AnalyzeController(InputView inputView, OutputView outputView, ResultFormatter formatter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.formatter = formatter;
        this.fileSaver = new FileSaver();
        this.sessionHistory = new StringBuilder();
        this.menuActions = new HashMap<>();

        initializeMenuActions();
    }

    public void initializeMenuActions() {
        menuActions.put(1, this::runStringAnalyzes);
        menuActions.put(2, this::runListMiddleInsertAnalyzes);
        menuActions.put(3, this::runListSequentialInsertAnalyzes);
        menuActions.put(4, this::runStreamAnalyzes);
        menuActions.put(5, this::runCollectionContainsAnalyzes);
        menuActions.put(6, this::runArrayCopyAnalyzes);
        menuActions.put(7, this::runMapGetAnalyzes);
        menuActions.put(8, this::runStringReplaceAnalyzes);
        menuActions.put(9, this::runListRemoveAnalyzes);
        menuActions.put(10, this::runParallelStreamAnalyzes);
    }

    public void run() {
        while (true) {
            outputView.printMainMenu();
            try {
                int userChoice = inputView.readMenuChoice();

                if (userChoice == 0) {
                    saveSessionReportIfNotEmpty();
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
        processAnalysis("1. String (+) vs. StringBuilder", GUIDE_VERY_SLOW,
                new StringConcatAnalyze(), new StringBuilderAppendAnalyze());
    }

    private void runListMiddleInsertAnalyzes() {
        processAnalysis("2. ArrayList vs. LinkedList (중간 삽입)", GUIDE_VERY_SLOW,
                new ArrayListMiddleInsertAnalyze(), new LinkedListMiddleInsertAnalyze());
    }

    private void runListSequentialInsertAnalyzes() {
        processAnalysis("3. ArrayList vs. LinkedList (순차 삽입)", GUIDE_FAST,
                new LinkedListSequentialInsertAnalyze(), new ArrayListSequentialInsertAnalyze());
    }

    private void runStreamAnalyzes() {
        processAnalysis("4. Stream.forEach vs. Enhanced For Loop", GUIDE_FAST,
                new StreamForEachAnalyze(), new EnhancedForLoopAnalyze());
    }

    private void runCollectionContainsAnalyzes() {
        processAnalysis("5. ArrayList.contains vs. HashSet.contains", GUIDE_SLOW,
                new ArrayListContainsAnalyze(), new HashSetContainsAnalyze());
    }

    private void runArrayCopyAnalyzes() {
        processAnalysis("6. For Loop vs. System.arraycopy (배열 대량 복사)", GUIDE_FAST,
                new ForLoopCopyAnalyze(), new SystemArrayCopyAnalyze());
    }

    private void runMapGetAnalyzes() {
        processAnalysis("7. TreeMap vs. HashMap (키 기반 Map 조회)", GUIDE_FAST,
                new TreeMapGetAnalyze(), new HashMapGetAnalyze());
    }

    private void runStringReplaceAnalyzes() {
        processAnalysis("8. replaceAll vs. replace (단순 문자열 치환)", GUIDE_FAST,
                new StringReplaceAllAnalyze(), new StringReplaceAnalyze());
    }

    private void runListRemoveAnalyzes() {
        processAnalysis("9. Index vs. Iterator (List 반복 중 삭제)", GUIDE_VERY_SLOW,
                new ArrayListRemoveByIndexAnalyze(), new ArrayListRemoveByIteratorAnalyze());
    }

    private void runParallelStreamAnalyzes() {
        processAnalysis("10. Stream vs. Parallel Stream (병렬 처리 오버헤드)", GUIDE_STREAM,
                new StreamForEachAnalyze(), new ParallelStreamAnalyze());
    }

    private void processAnalysis(String title, String guide, PerformanceAnalyze analyze1, PerformanceAnalyze analyze2) {
        outputView.printAnalyzeStart(title);

        int iterations;
        try {
            iterations = inputView.readInputIterations(guide);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return;
        }

        AnalyzeResult result1 = executeAndPrint(analyze1, iterations);
        AnalyzeResult result2 = executeAndPrint(analyze2, iterations);

        String comparison = formatter.formatComparison(result1, result2);
        outputView.printAnalyzeResult(comparison);

        appendToHistory(title, result1, result2, comparison);
    }

    private AnalyzeResult executeAndPrint(PerformanceAnalyze analyze, int iterations) {
        AnalyzeResult result = analyze.runAnalyze(iterations);
        String formattedResult = formatter.formatSingleResult(result);
        outputView.printAnalyzeResult(formattedResult);
        return result;
    }

    private void appendToHistory(String title, AnalyzeResult r1, AnalyzeResult r2, String comparison) {
        sessionHistory.append("## ").append(title).append("\n\n");
        sessionHistory.append(formatter.formatSingleResult(r1)).append("\n");
        sessionHistory.append(formatter.formatSingleResult(r2)).append("\n");
        sessionHistory.append(comparison).append("\n");
        sessionHistory.append("\n--------------------------------------------------\n\n");
    }

    private void saveSessionReportIfNotEmpty() {
        if (sessionHistory.length() == 0) {
            return;
        }

        System.out.println("\n모든 분석이 종료되었습니다.");

        if (inputView.confirmSave()) {
            try {
                String path = fileSaver.save("Session_Report", sessionHistory.toString());
                System.out.println("전체 분석 결과가 저장되었습니다: " + path);
            } catch (IOException e) {
                outputView.printError("파일 저장 중 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }
}
