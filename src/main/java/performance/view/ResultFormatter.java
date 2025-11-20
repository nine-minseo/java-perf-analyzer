package performance.view;

import performance.AnalyzeResult;

public class ResultFormatter {
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

        return String.format("[분석 결과] %s 방식이 %s 방식보다 약 %.2f배 더 빠릅니다.",
                fast.getAnalyzeName(), slow.getAnalyzeName(), multiple);
    }

    private String formatTime(double durationMs) {
        if (durationMs >= 1000) {
            return String.format("%.4f s", durationMs / 1000.0);
        }
        return String.format("%.4f ms", durationMs);
    }
}
