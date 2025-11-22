package performance.analysis.string;

import performance.model.BasePerformanceAnalyze;

public class StringBuilderAppendAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "StringBuilder (.append)";
    private static final String APPEND_STRING = "a";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(int iterations) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(APPEND_STRING);
        }
    }
}
