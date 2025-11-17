package performance.analysis;

import performance.AnalyzeResult;
import performance.PerformanceAnalyze;

public class StringConcatAnalyze implements PerformanceAnalyze {
    private static final String ANALYZE_NAME = "String 덧셈";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    public AnalyzeResult runAnalyze(long iterations) {
        long startTime = System.nanoTime();

        String emptyString = "";

        for (long i = 0; i < iterations; i++) {
            emptyString += "a";
        }

        long endTime = System.nanoTime();

        long durationNano = 0;

        return new AnalyzeResult(ANALYZE_NAME, durationNano, iterations);
    }
}
