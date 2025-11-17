package performance.analysis;

import performance.AnalyzeResult;
import performance.PerformanceAnalyze;

public class StringBuilderAppendAnalyze implements PerformanceAnalyze {
    private static final String ANALYZE_NAME = "StringBuilder 추가";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    public AnalyzeResult runAnalyze(long iterations) {
        long startTime = System.nanoTime();

        StringBuilder stringBuilder = new StringBuilder();
        for (long i = 0; i < iterations; i++) {
            stringBuilder.append("a");
        }

        long endTime = System.nanoTime();

        long durationNano = endTime - startTime;

        return new AnalyzeResult(ANALYZE_NAME, durationNano, iterations);
    }
}
