package performance.view;

import performance.model.AnalyzeResult;

public class ResultFormatter {
    private static final int MAX_BAR_LENGTH = 50;
    private static final String BLOCK_CHAR = "█";
    private static final int LABEL_WIDTH = 30;

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    public String formatSingleResult(AnalyzeResult result) {
        String timeString = formatTime(result.getDurationMs());

        return String.format("%s: %,d회 반복, %s 소요",
                result.getAnalyzeName(), result.getIterations(), timeString);
    }

    public String formatComparison(AnalyzeResult result1, AnalyzeResult result2) {
        if (result1.getIterations() != result2.getIterations()) {
            return "[비교 불가] 두 분석의 반복 횟수가 달라 성능을 비교할 수 없습니다.";
        }

        AnalyzeResult fast = result1.getDurationNano() < result2.getDurationNano() ? result1 : result2;
        AnalyzeResult slow = result1 == fast ? result2 : result1;

        double fastTime = Math.max(fast.getDurationMs(), 0.0001);
        double slowTime = slow.getDurationMs();

        double multiple = slowTime / fastTime;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[분석 결과] %s[%s]%s 방식이 %s[%s]%s 방식보다 약 %s%.2f배%s 더 빠릅니다",
                GREEN, fast.getAnalyzeName(), RESET,
                RED, slow.getAnalyzeName(), RESET,
                YELLOW, multiple, RESET));

        stringBuilder.append("\n\n[성능 시각화]\n");
        stringBuilder.append(drawGraph(slow, slowTime, slowTime, RED));
        stringBuilder.append("\n");
        stringBuilder.append(drawGraph(fast, fastTime, slowTime, GREEN));

        return stringBuilder.toString();
    }

    private String drawGraph(AnalyzeResult result, double targetTime, double standardTime, String color) {
        double ratio = targetTime / standardTime;
        int barLength = (int) (ratio * MAX_BAR_LENGTH);

        if (barLength == 0 && targetTime > 0) {
            barLength = 1;
        }

        String label = String.format("%-" + LABEL_WIDTH + "s", result.getAnalyzeName());

        return String.format("%s | %s%s%s %s",
                label,
                color, BLOCK_CHAR.repeat(barLength), RESET,
                formatTime(targetTime));
    }

    private String formatTime(double durationMs) {
        if (durationMs >= 1000) {
            return String.format("%.4f s", durationMs / 1000.0);
        }
        return String.format("%.4f ms", durationMs);
    }
}
