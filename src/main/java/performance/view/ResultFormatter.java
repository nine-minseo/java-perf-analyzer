package performance.view;

import performance.AnalyzeResult;

public class ResultFormatter {
    public String formatSingleResult(AnalyzeResult result) {
        return String.format("%s: %,d회 반복, %.4f ms 소요",
                result.getAnalyzeName(), result.getIterations(), result.getDurationMs());
    }
}
