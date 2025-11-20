package performance.analysis.string;

import performance.model.BasePerformanceAnalyze;

public class StringConcatAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "String 덧셈";
    private static final String APPEND_STRING = "a";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(long iterations) {
        String emptyString = "";

        for (long i = 0; i < iterations; i++) {
            emptyString += APPEND_STRING;
        }
    }
}

