package performance.analysis.regex;

import performance.model.BasePerformanceAnalyze;

public class StringReplaceAnalyze extends BasePerformanceAnalyze {
    private final String source;
    private static final String ANALYZE_NAME = "String.replace";

    public StringReplaceAnalyze() {
        this.source = "Java is a programming language. Java is widely used. Java is fun.";
    }

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(int iterations) {
        for (int i = 0; i < iterations; i++) {
            source.replace("Java", "Kotlin");
        }
    }
}