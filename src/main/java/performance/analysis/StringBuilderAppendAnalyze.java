package performance.analysis;

import performance.BasePerformanceAnalyze;

public class StringBuilderAppendAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "StringBuilder (.append)";
    private static final String APPEND_STRING = "a";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(long iterations) {
        StringBuilder stringBuilder = new StringBuilder();

        for (long i = 0; i < iterations; i++) {
            stringBuilder.append(APPEND_STRING);
        }
    }
}
